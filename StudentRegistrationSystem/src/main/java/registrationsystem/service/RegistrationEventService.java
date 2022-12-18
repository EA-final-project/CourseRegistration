package registrationsystem.service;

import registrationsystem.domain.Course;
import registrationsystem.domain.RegistrationEvent;
import registrationsystem.domain.RegistrationRequest;
import registrationsystem.domain.RegistrationStatus;
import registrationsystem.service.dto.CourseDTO;
import registrationsystem.service.dto.RegistrationEventDTO;
import registrationsystem.service.dto.StudentDetailDTO;

import java.time.LocalDate;
import java.util.Collection;

public interface RegistrationEventService {

    String recentRegistrationEvent();
    void deleteRegistryEvent(Long id);
    RegistrationEventDTO getRegistrationEvent(Long id);
    Collection<RegistrationEventDTO> getAllRegistrationEvent();
    void addRegistrationEvent(RegistrationEvent registrationEvent);
    //StudentDetailDTO readRegistrationEvent(Long studentId, String groupName);
    RegistrationEventDTO updateRegistryEvent(Long id, RegistrationEvent registrationEvent);
    void submitRegistration(Collection<RegistrationRequest> requests, Collection<Course> courses);
}
