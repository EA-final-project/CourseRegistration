package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.service.RegistrationService;

@RestController
@RequestMapping("/processed/registrations")
public class RegistrationServiceController {
    @Autowired
    private RegistrationService registrationService;


    public ResponseEntity<?> getRegistration(@PathVariable Long id) {
        var registration = registrationService.getRegistration(id);
        if (registration == null) {
            throw new CourseExceptionHandler("Registration with id: " + id + " not found");
        }
        return ResponseEntity.ok(registration);
    }

    @GetMapping
    public ResponseEntity<?> getAllRegistrations() {
        var allRegistrations = registrationService.allRegistration();
        return ResponseEntity.ok(allRegistrations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegistration(@PathVariable Long id) {
        var registration = registrationService.getRegistration(id);
        if (registration == null) {
            throw new CourseExceptionHandler("Registration with id: " + id + " not found");
        }
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
