package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.Student;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.UserService;

@RestController
public class UserController {
    @Autowired
    private RegistrationEventService registrationEventService;
    @Autowired
    private UserService userService;

    @GetMapping("/registrations/list")
    public ResponseEntity<?> listRegistration() {
        var allList = registrationEventService.getAllRegistrationEvent();
        return ResponseEntity.ok(allList);
    }

    @GetMapping("/read-registrations/{studentId}/track")
    public ResponseEntity<?> readRegistration(@PathVariable Long studentId, @PathVariable String track) {
        var result = userService.readRegistrationEvent(studentId, track);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/registration-events/{id}")
    public ResponseEntity<?> processRequest(@PathVariable Long id, @RequestParam boolean processed) {
        userService.processRegistrationRequest(id,processed);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Student Controller start HERE
     *
     * @param student
     * @return
     */

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        userService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/students/get/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable Long studentId) {
        var student = userService.getStudent(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudent() {
        var allStudent = userService.getAllStudents();
        return ResponseEntity.ok(allStudent);
    }

    @PutMapping("/students/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        var foundStudent = userService.updateStudent(studentId, student);
        if (foundStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/students/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        userService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
