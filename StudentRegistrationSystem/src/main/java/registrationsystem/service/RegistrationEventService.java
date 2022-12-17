package registrationsystem.service;

import registrationsystem.domain.RegistrationEvent;

import java.time.LocalDate;

public interface RegistrationEventService {

    void addRegistrationEvent(RegistrationEvent registrationEvent);
    RegistrationEvent getRegistrationEvent();
}
