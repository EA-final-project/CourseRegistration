package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.Student;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RegistrationEventService registrationEventService;

    @GetMapping("/registration-events/latest")
    public ResponseEntity<?> currentRegistrationEvent() {
        var currentEvent = registrationEventService.recentRegistrationEvent();
        return ResponseEntity.ok(currentEvent);
    }

    @GetMapping("/registrations/list")
    public ResponseEntity<?> listRegistration() {
        var allList = registrationEventService.getAllRegistrationEvent();
        return ResponseEntity.ok(allList);
    }

    @GetMapping("/read-registrations/{studentId}/track")
    public ResponseEntity<?> readRegistration(@PathVariable Long studentId, @PathVariable String track) {
        var result = studentService.readRegistrationEvent(studentId, track);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Student Controller start HERE
     * @param student
     * @return
     */

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/students/get/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable Long studentId) {
        var student = studentService.getStudent(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudent(){
        var allStudent = studentService.getAllStudents();
        return ResponseEntity.ok(allStudent);
    }
    @PutMapping("/students/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        var foundStudent = studentService.updateStudent(studentId, student);
        if (foundStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/students/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
