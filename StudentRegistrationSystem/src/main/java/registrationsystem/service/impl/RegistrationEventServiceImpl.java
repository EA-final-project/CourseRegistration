package registrationsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.RegistrationEvent;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.service.RegistrationEventService;

import java.time.LocalDate;

@Service
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    private RegistrationEventRepository registrationEventRepository;

    @Override
    public void addRegistrationEvent(RegistrationEvent registrationEvent) {
        registrationEventRepository.save(registrationEvent);
    }

    @Override
    public RegistrationEvent getRegistrationEvent() {
        var allEvents = registrationEventRepository.findAll();
        //capturing current date as Integer
        int startDate = LocalDate.now().getDayOfMonth();
        int endDate = LocalDate.now().getDayOfMonth();

        return allEvents.stream()
                .filter(event -> event.getStartDate().getDayOfMonth() >= startDate && event.getEndDate().getDayOfMonth() <= endDate)
                .sorted((c1,c2) -> c2.getStartDate().compareTo(c1.getStartDate()))
                .findFirst().get();
    }
}
