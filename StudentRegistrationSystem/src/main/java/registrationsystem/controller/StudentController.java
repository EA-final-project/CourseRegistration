package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import registrationsystem.service.RegistrationEventService;

@RestController
public class StudentController {
    @Autowired
    private RegistrationEventService registrationEventService;
    @GetMapping("/registration-events/latest")
    public ResponseEntity<?> currentRegistrationEvent() {
        var currentEvent = registrationEventService.recentRegistrationEvent();
        return ResponseEntity.ok(currentEvent);
    }
}
