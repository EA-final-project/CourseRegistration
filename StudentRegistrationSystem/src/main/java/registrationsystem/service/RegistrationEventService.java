package registrationsystem.service;

import registrationsystem.domain.*;
import registrationsystem.service.dto.*;

import java.time.LocalDate;
import java.util.Collection;

public interface RegistrationEventService {

    String recentRegistrationEvent();
    void deleteRegistryEvent(Long id);
    RegistrationEventDTO getRegistrationEvent(Long id);
    Collection<RegistrationEventDTO> getAllRegistrationEvent();
    void addRegistrationEvent(RegistrationEvent registrationEvent);
    Collection<CourseOfferingDTO> readRegistrationEvent(Long studentId, String groupName);
    RegistrationEventDTO updateRegistryEvent(Long id, RegistrationEvent registrationEvent);
    void submitRegistration(Collection<RegistrationRequest> requests, Collection<Course> courses);
}
