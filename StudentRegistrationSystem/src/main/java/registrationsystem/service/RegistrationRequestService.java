package registrationsystem.service;

import registrationsystem.domain.RegistrationRequest;
import registrationsystem.service.dto.RegistrationRequestDTO;

import java.util.Collection;

public interface RegistrationRequestService {
    void deleteRegistrationRequest(Long id);
    RegistrationRequestDTO getRegistrationRequest(Long id);
    void addRegistrationRequest(RegistrationRequest request);
    Collection<RegistrationRequestDTO> getAllRegistrationRequest();
    RegistrationRequestDTO updateRequest(Long id, RegistrationRequest request);

}
