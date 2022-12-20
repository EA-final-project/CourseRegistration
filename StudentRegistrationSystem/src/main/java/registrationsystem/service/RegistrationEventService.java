package registrationsystem.service;

import registrationsystem.domain.*;
import registrationsystem.service.dto.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

public interface RegistrationEventService {

    RegistrationStatus recentRegistrationEvent();
    void deleteRegistryEvent(Long id);
    RegistrationEventDTO getRegistrationEvent(Long id);
    Collection<RegistrationEventDTO> getAllRegistrationEvent();
    void addRegistrationEvent(RegistrationEvent registrationEvent);
    Collection<RegistrationEventDTO> getRegistrationEventByStudentId(String studentId);
    Collection<CourseOfferingDTO> readRegistrationEvent(String studentId, String track);
    RegistrationEventDTO updateRegistryEvent(Long id, RegistrationEvent registrationEvent);
    void submitRegistration(Collection<RegistrationRequest> requests, HashMap<Boolean,Course> selected);

}
