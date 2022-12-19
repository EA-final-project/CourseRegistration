package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.*;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.RegistrationRequestRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.dto.CourseOfferingDTO;
import registrationsystem.service.dto.RegistrationEventDTO;
import registrationsystem.service.dto.StudentDTO;
import registrationsystem.service.dto.StudentDetailDTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addRegistrationEvent(RegistrationEvent registrationEvent) {
        registrationEventRepository.save(registrationEvent);
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
        }
        return modelMapper.map(event, RegistrationEventDTO.class);
    }

    @Override
    public void submitRegistration(Collection<RegistrationRequest> requests, HashMap<Boolean, Course> selected) {

        if (recentRegistrationEvent() == RegistrationStatus.OPEN) {

            for (RegistrationRequest request : requests) {
                CourseOffering offering = request.getCourseOffering();

                if (offering.getCourses().size() == 1) {
                    Course selectedCourse = offering.getCourses().stream()
                            .toList().stream()
                            .findFirst().get();
                    System.out.println("Selected Course " + selectedCourse);
                    registrationRequestRepository.save(request);
                }
            }
        }
    }

    @Override
    public RegistrationEventDTO getRegistrationEvent(Long id) {
        var event = registrationEventRepository.findById(id);
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

        int currentTime = LocalDate.now().getDayOfMonth(); // 18
        int startDate = latestEvent.getStartDate().getDayOfMonth(); // 16
        int endDate = latestEvent.getEndDate().getDayOfMonth(); //19

        if (currentTime >= startDate && currentTime <= endDate) {
            return RegistrationStatus.OPEN;
        } else if (currentTime > startDate && currentTime > endDate) {
            return RegistrationStatus.CLOSED;
        } else {
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
    public Collection<CourseOfferingDTO> readRegistrationEvent(Long studentId, String groupName) {
        var allEvent = registrationEventRepository.findById(studentId);

        return allEvent.stream()
                .flatMap(event -> event.getRegistrationGroups().stream())
                .filter(group -> group.getGroupName().equals(groupName))
                .flatMap(block -> block.getAcademicBlocks().stream())
                .flatMap(stu -> stu.getCourseOfferings().stream())
                .map(st -> modelMapper.map(st, CourseOfferingDTO.class))
                .collect(Collectors.toList());
    }
}
