package registrationsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import registrationsystem.domain.RegistrationGroup;
import registrationsystem.repository.RegistrationGroupRepository;
import registrationsystem.repository.RegistrationRequestRepository;
import registrationsystem.service.RegistrationGroupService;

@Service
public class RegistrationGroupServiceImpl implements RegistrationGroupService {
    @Autowired
    private RegistrationGroupRepository registrationGroupRepository;

    @Override
    public void addRegistrationGroup(RegistrationGroup group) {
        registrationGroupRepository.save(group);
    }
}
