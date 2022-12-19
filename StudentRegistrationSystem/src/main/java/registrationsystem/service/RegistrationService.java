package registrationsystem.service;

import registrationsystem.domain.Registration;
import registrationsystem.service.dto.RegistrationDTO;

import java.util.Collection;

public interface RegistrationService {

   void deleteRegistration(Long id);
    RegistrationDTO getRegistration(Long id);
    Collection<RegistrationDTO> allRegistration();
}
