package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.*;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.kafkaSender.KafkaSenderService;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.RegistrationService;
import registrationsystem.service.dto.CourseOfferingDTO;
import registrationsystem.service.dto.RegistrationEventDTO;
import registrationsystem.util.ConvertToRegistration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationEventServiceImpl implements RegistrationEventService {

    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private ConvertToRegistration convertToRegistration;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public void addRegistrationEvent(RegistrationEvent registrationEvent) {
        registrationEventRepository.save(registrationEvent);
    }
    @Override
    public void processRegistrationEvent(Long eventId,boolean processed) {

        var eventToProcess = registrationEventRepository.findById(eventId).get();
        if (eventToProcess == null) {
            throw new CourseExceptionHandler("The Registration Event with id " + eventId + " not found");
        }
        //convertor
        Collection<RegistrationRequest> allRequests = new ArrayList<>();
        Collection<CourseOffering> allCourseOfferings = new ArrayList<>();
        Collection<RegistrationGroup> allGroups = new ArrayList<>();
        RegistrationStatus  recentEvent = recentRegistrationEvent();

        //the event must be closed
        if (recentEvent == RegistrationStatus.CLOSED) {

            //getting all the offering
            for(RegistrationGroup groups: eventToProcess.getRegistrationGroups()){
                allGroups.add(groups);
                for(AcademicBlock blocks: groups.getAcademicBlocks()){
                    for(CourseOffering offering: blocks.getCourseOfferings()){
                        allCourseOfferings.add(offering);
                    }
                }
            }
            //getting all the requests
            for(RegistrationGroup group: allGroups){
                for(Student student: group.getStudents()){
                    for(RegistrationRequest request: student.getRegistrationRequests()){
                        allRequests.add(request);
                    }
                }
            }
            Registration convertedRequest = convertToRegistration.convertor(allRequests,allCourseOfferings);
            registrationService.saveRegistration(convertedRequest);

            /**
             * send to kafka ----> ????
             */
        } else {
            throw new CourseExceptionHandler("The Registration Event is currently open or it is in-progress");
        }
    }
    @Override
    public Collection<RegistrationEventDTO> getRegistrationEventByStudentId(String studentId) {
        var allRegistrations = registrationEventRepository.findRegistrationEventsById(studentId); //working

        if (allRegistrations == null) {
            throw new CourseExceptionHandler("No registrationEvent found for student with id " + studentId + " not found");
        }
        //var student = studentRepository.findStudentByStudentId(studentId);
//        return student.stream()
//                .flatMap(gr -> gr.getRegistrationGroups().stream())
//                .flatMap(block -> block.getAcademicBlocks().stream())
//                .flatMap(c -> c.getCourseOfferings().stream())
//                .map(offer -> modelMapper.map(offer, CourseOfferingDTO.class))
//                .collect(Collectors.toList());

        return allRegistrations.stream()
                .map(event -> modelMapper.map(event, RegistrationEventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationEventDTO updateRegistryEvent(Long id, RegistrationEvent registrationEvent) {
        var event = registrationEventRepository.findById(id).get();
        if (event != null) {
            event.setId(registrationEvent.getId());
            event.setStartDate(registrationEvent.getStartDate());
            event.setEndDate(registrationEvent.getEndDate());
            event.setRegistrationGroups(registrationEvent.getRegistrationGroups());
            registrationEventRepository.save(event);
        } else {
            throw new CourseExceptionHandler("Registration with id " + id + " not found");
        }
        return modelMapper.map(event, RegistrationEventDTO.class);
    }

    @Override
    public RegistrationEventDTO getRegistrationEvent(Long id) {
        var event = registrationEventRepository.findById(id);
        if (event == null) {
            throw new CourseExceptionHandler("Registration with id " + id + " not found");
        }
        return modelMapper.map(event, RegistrationEventDTO.class);
    }

    @Override
    public Collection<RegistrationEventDTO> getAllRegistrationEvent() {
        var allEvents = registrationEventRepository.findAll();

        return allEvents.stream()
                .map(events -> modelMapper.map(events, RegistrationEventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationStatus recentRegistrationEvent() {

        var latestEvent = registrationEventRepository.findFirstByOrderByStartDateAsc();

        LocalDate currentTime = LocalDate.now(); // 18
        LocalDate startDate = latestEvent.getStartDate();
        LocalDate endDate = latestEvent.getEndDate();

        if (currentTime.isAfter(startDate) && currentTime.isBefore(endDate)) {
            log.info("Open registration");
            return RegistrationStatus.OPEN;
        } else if (currentTime.isAfter(startDate) && currentTime.isAfter(endDate)) {
            log.info("closed registration");
            return RegistrationStatus.CLOSED;
        } else {
            log.info("In-progress registration");
            return RegistrationStatus.INPROGRESS;
        }
    }

    @Override
    public void deleteRegistryEvent(Long id) {
        var event = registrationEventRepository.findById(id);

        if (event.isPresent()) {
            registrationEventRepository.deleteById(id);
        }
    }


    @Override
    public Collection<CourseOfferingDTO> readRegistrationEvent(String studentId, String track) {
        var allEvent = studentRepository.findStudentByStudentId(studentId);

        return allEvent.stream()
                .flatMap(event -> event.getRegistrationGroups().stream())
                .filter(group -> group.getTrack().equals(track))
                .flatMap(block -> block.getAcademicBlocks().stream())
                .flatMap(stu -> stu.getCourseOfferings().stream())
                .map(st -> modelMapper.map(st, CourseOfferingDTO.class))
                .collect(Collectors.toList());
    }
}
