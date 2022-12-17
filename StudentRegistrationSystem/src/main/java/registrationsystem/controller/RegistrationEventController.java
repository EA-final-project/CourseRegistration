package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.RegistrationEvent;
import registrationsystem.service.RegistrationEventService;

import java.time.LocalDate;

@RestController
@RequestMapping("/registration-events")
public class RegistrationEventController {
    @Autowired
    private RegistrationEventService registrationEventService;

    @PostMapping
    public ResponseEntity<?> addRegistrationEvent(@RequestBody RegistrationEvent event) {
        registrationEventService.addRegistrationEvent(event);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> checkingRegistrationEvent() {
        var currentEvent = registrationEventService.getRegistrationEvent();
        return ResponseEntity.ok(currentEvent);
    }
}
