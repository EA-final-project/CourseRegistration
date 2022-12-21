package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.*;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.kafkaSender.KafkaSenderService;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.RegistrationRequestRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.RegistrationRequestService;
import registrationsystem.service.dto.RegistrationRequestDTO;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationRequestServiceImpl implements RegistrationRequestService {
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private RegistrationRequestRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RegistrationEventService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Override
    public void deleteRegistrationRequest(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RegistrationRequestDTO getRegistrationRequest(Long id) {
        var request = repository.findById(id);
        if (request == null) {
            throw new CourseExceptionHandler("Registration Request with id " + id + " not found");
        }
        return modelMapper.map(request, RegistrationRequestDTO.class);
    }

    @Override
    public String saveRegistrationRequest(Collection<RegistrationRequest> requests, String studentId) { //not fully working

        Student student = studentRepository.findStudentByStudentId(studentId).get();
        Set<Long> offerId = new HashSet<>(); //to avoid duplication

        RegistrationEvent latest = registrationEventRepository.findFirstByOrderByStartDateAsc();
        RegistrationStatus recentStatus = service.recentRegistrationEvent();

        if (recentStatus.equals(RegistrationStatus.OPEN)) {
            Collection<RegistrationGroup> groups = latest.getRegistrationGroups();

            for(RegistrationGroup group: groups){
                Collection<AcademicBlock> blocks = group.getAcademicBlocks();
                for(AcademicBlock block: blocks){
                    Collection<CourseOffering> offers = block.getCourseOfferings();
                    for(CourseOffering offer: offers){
                        offerId.add(offer.getId());
                    }
                }
            }

            for (RegistrationRequest request : requests) {
                if (offerId.contains(request.getCourseOffering().getId())) {
                    student.getRegistrationRequests().add(request);
                    studentRepository.save(student);
                    registrationRequestRepository.save(request);
                    log.info("Student with id: " + studentId + " and request Id: " + "saved");
                }
            }
            StudentDetails studentDetails = new StudentDetails(
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    latest.getId(),
                    latest.getStartDate().toString(),
                    latest.getEndDate().toString()
            );
            /**
             * sending studentDetails to Kafka
             */
            kafkaSenderService.sendStudentDetails("student_details1",studentDetails);
            return "Request saved successfully";
        } else {
            return "not saved";
        }
    }

    @Override
    public Collection<RegistrationRequestDTO> getAllRegistrationRequest() {
        var allRequests = repository.findAll();

        return allRequests.stream()
                .map(request -> modelMapper.map(request, RegistrationRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationRequestDTO updateRequest(Long id, RegistrationRequest request) {
        var updateRequest = repository.findById(id).get();

        if (updateRequest != null) {
            updateRequest.setId(request.getId());
            updateRequest.setPriorityNumber(request.getPriorityNumber());
            repository.save(updateRequest);
        } else {
            throw new CourseExceptionHandler("Registration Request with id " + id + " not found");
        }
        return modelMapper.map(updateRequest, RegistrationRequestDTO.class);
    }
}
