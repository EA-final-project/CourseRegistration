package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.CourseOffering;
import registrationsystem.domain.Registration;
import registrationsystem.domain.Student;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.service.RegistrationService;
import registrationsystem.service.dto.CourseOfferingDTO;
import registrationsystem.service.dto.RegistrationDTO;
import registrationsystem.service.dto.StudentDTO;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public void saveRegistration(Registration registration) {
        registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(Long id) {
        var registration = registrationRepository.findById(id);
        if (registration == null) {
            System.out.println("Registration with id : " + " not found");
        }
        registrationRepository.deleteById(id);
    }

    @Override
    public Registration getRegistration(Long id) {
        return registrationRepository.findById(id).get();
    }

    @Override
    public Collection<Registration> allRegistration() {
        return registrationRepository.findAll();
    }

}
