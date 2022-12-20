package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.RegistrationEvent;
import registrationsystem.service.RegistrationEventService;


@RestController
@RequestMapping("/registrations")
public class RegistrationEventController {

    @Autowired
    private RegistrationEventService registrationEventService;

//    @GetMapping("/read/{studentId}") //fixme -----> not working
//    public ResponseEntity<?> readRegistrationEvent(@PathVariable String studentId) {
//        var readEvent = registrationEventService.getRegistrationEventByStudentId(studentId);
//
//        if (readEvent == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(readEvent);
//    }

    @PostMapping
    public ResponseEntity<?> addRegistrationEvent(@RequestBody RegistrationEvent event) {
        registrationEventService.addRegistrationEvent(event);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{studentId}") //fixme--->test
    public ResponseEntity<?> getRegistrationEvent(@PathVariable String studentId, @RequestParam String track) {
        var event = registrationEventService.readRegistrationEvent(studentId,track);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        var allEvents = registrationEventService.getAllRegistrationEvent();
        return ResponseEntity.ok(allEvents);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody RegistrationEvent event) {
        var updateEvent = registrationEventService.updateRegistryEvent(id, event);
        return ResponseEntity.ok(updateEvent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        registrationEventService.deleteRegistryEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
