package registrationsystem.service;

import registrationsystem.domain.RegistrationRequest;
import registrationsystem.service.dto.RegistrationRequestDTO;

import java.util.Collection;
import java.util.List;

public interface RegistrationRequestService {
    void deleteRegistrationRequest(Long id);
    RegistrationRequestDTO getRegistrationRequest(Long id);
    Collection<RegistrationRequestDTO> getAllRegistrationRequest();
    String saveRegistrationRequest(Collection<RegistrationRequest> requests, String studentId);
    RegistrationRequestDTO updateRequest(Long id, RegistrationRequest request);

}
