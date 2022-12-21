package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Registration;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.service.RegistrationService;
import registrationsystem.service.dto.RegistrationDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveRegistration(Registration registration) {
        registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(Long id) {
        var registration = registrationRepository.findById(id);
        if(registration == null){
            throw new CourseExceptionHandler("Registration with id : " + " not found");
        }
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDTO getRegistration(Long id) {
        var registration = registrationRepository.findById(id);
        if(registration == null){
            throw new CourseExceptionHandler("Registration with id : " + " not found");
        }
        return modelMapper.map(registration,RegistrationDTO.class);
    }

    @Override
    public Collection<RegistrationDTO> allRegistration() {
        var allRegistration = registrationRepository.findAll();

        return allRegistration.stream()
                .map(reg -> modelMapper.map(reg, RegistrationDTO.class))
                .collect(Collectors.toList());
    }

}
