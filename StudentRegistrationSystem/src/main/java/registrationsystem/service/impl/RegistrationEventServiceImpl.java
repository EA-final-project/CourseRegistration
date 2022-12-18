package registrationsystem.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Course;
import registrationsystem.domain.RegistrationEvent;
import registrationsystem.domain.RegistrationRequest;
import registrationsystem.domain.RegistrationStatus;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.dto.RegistrationEventDTO;
import registrationsystem.service.dto.StudentDetailDTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    private RegistrationEventRepository registrationEventRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StudentRepository studentRepository;

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
    public void submitRegistration(Collection<RegistrationRequest> requests, Collection<Course> courses) {
/**
 *
 * todo              ----------------- ---->>>>>
 */
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
    public String recentRegistrationEvent() {

        var latestEvent = registrationEventRepository.findFirstByOrderByStartDateDesc();

        LocalDate currentTime = LocalDate.now();

        if (currentTime.isBefore(latestEvent.getStartDate()) && currentTime.isBefore(latestEvent.getEndDate())) {
            return latestEvent + " " + RegistrationStatus.INPROGRESS;
        } else if (currentTime.isAfter(latestEvent.getStartDate()) && currentTime.isAfter(latestEvent.getEndDate())) {
            return latestEvent + " " + RegistrationStatus.CLOSED;
        } else {
            return latestEvent + " " + RegistrationStatus.OPEN;
        }
    }

    @Override
    public void deleteRegistryEvent(Long id) {
        var event = registrationEventRepository.findById(id);

        if (event.isPresent()) {
            registrationEventRepository.deleteById(id);
        }
    }

//    @Override
//    public StudentDetailDTO readRegistrationEvent(Long studentId, String groupName) {
//        var allEvent = registrationEventRepository.findAll();
//        allEvent.stream()
//                .flatMap(event -> event.getRegistrationGroups().stream())
//                .filter(group -> group.getGroupName().equals(groupName))
//                .flatMap(stu -> stu.getStudents().stream())
//
//
//
//    }

}
