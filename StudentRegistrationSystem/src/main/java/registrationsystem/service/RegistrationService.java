package registrationsystem.service;

import registrationsystem.domain.Registration;
import registrationsystem.service.dto.RegistrationDTO;

import java.util.Collection;

public interface RegistrationService {

    void saveRegistration(Registration registration);
   void deleteRegistration(Long id);
    Registration getRegistration(Long id);
    Collection<Registration> allRegistration();
}
