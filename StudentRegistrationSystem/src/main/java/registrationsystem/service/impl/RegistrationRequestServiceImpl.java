package registrationsystem.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.RegistrationRequest;
import registrationsystem.repository.RegistrationRequestRepository;
import registrationsystem.service.RegistrationRequestService;
import registrationsystem.service.dto.RegistrationRequestDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deleteRegistrationRequest(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RegistrationRequestDTO getRegistrationRequest(Long id) {
        var request = repository.findById(id);
        return modelMapper.map(request, RegistrationRequestDTO.class);
    }

    @Override
    public void addRegistrationRequest(RegistrationRequest request) {
        repository.save(request);
    }

    @Override
    public Collection<RegistrationRequestDTO> getAllRegistrationRequest() {
        var allRequests = repository.findAll();

        return allRequests.stream()
                .map(request -> modelMapper.map(request, RegistrationRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationRequestDTO updateRequest(Long id, RegistrationRequest request) {
        var updateRequest = repository.findById(id).get();

        if(updateRequest != null){
            updateRequest.setId(request.getId());
            updateRequest.setPriorityNumber(request.getPriorityNumber());
            repository.save(updateRequest);
        }
        return modelMapper.map(updateRequest, RegistrationRequestDTO.class);
    }
}
