package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.Student;
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

//
//    @PostMapping
//    public ResponseEntity<?> addStudent(@RequestBody Student student) {
//        studentService.addStudent(student);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get/{studentId}")
//    public ResponseEntity<?> getStudent(@PathVariable Long studentId) {
//        var student = studentService.getStudent(studentId);
//        if (student == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(student);
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAllStudent(){
//        var allStudent = studentService.getAllStudents();
//        return ResponseEntity.ok(allStudent);
//    }
//    @PutMapping("/update/{studentId}")
//    public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
//        var foundStudent = studentService.updateStudent(studentId, student);
//        if (foundStudent == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("/delete/{studentId}")
//    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
//        studentService.deleteStudent(studentId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
