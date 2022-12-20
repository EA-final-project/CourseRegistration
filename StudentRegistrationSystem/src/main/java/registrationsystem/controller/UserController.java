package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.CourseOffering;
import registrationsystem.domain.Registration;
import registrationsystem.domain.RegistrationRequest;
import registrationsystem.domain.Student;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.repository.RegistrationRequestRepository;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.UserService;
import registrationsystem.util.ConvertToRegistration;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    private RegistrationEventService registrationEventService;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private ConvertToRegistration convertToRegistration;
    @Autowired
    private UserService userService;

    @GetMapping("/registration-events/list") //working
    public ResponseEntity<?> listRegistration() {
        var allList = registrationEventService.getAllRegistrationEvent();
        return ResponseEntity.ok(allList);
    }

    @GetMapping("/read-registrations/{studentId}") //working
    public ResponseEntity<?> readRegistration(@PathVariable String studentId) {
        var result = registrationEventService.getRegistrationEventByStudentId(studentId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/read-registrations/{studentId}/track")
//    public ResponseEntity<?> readRegistration(@PathVariable Long studentId, @PathVariable String track) {
//        var result = userService.readRegistrationEvent(studentId, track);
//        if (result == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(result);
//    }

    @PatchMapping("/registration-events/{id}") //fixme -----> partially working
    public ResponseEntity<?> processRequest(@PathVariable Long id, @RequestParam boolean processed) {
        //userService.processRegistrationRequest(id, processed);
        if (processed == true) {
            var listRequests = registrationRequestRepository.findAll();

            Collection<CourseOffering> listOffering = listRequests.stream()
                    .map(req -> req.getCourseOffering())
                    .collect(Collectors.toList());

            Registration registration = convertToRegistration.convertor(listRequests, listOffering);
            registrationRepository.save(registration);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Student Controller start HERE
     *
     * @param student
     * @return
     */

    @PostMapping("/students") //working
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        userService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/students/get/{studentId}") //working
    public ResponseEntity<?> getStudent(@PathVariable String studentId) {
        var student = userService.getStudent(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students") //working
    public ResponseEntity<?> getAllStudent() {
        var allStudent = userService.getAllStudents();
        return ResponseEntity.ok(allStudent);
    }

    @PutMapping("/students/update/{studentId}") //
    public ResponseEntity<?> updateStudent(@PathVariable String studentId, @RequestBody Student student) {
        var foundStudent = userService.updateStudent(studentId, student);
        if (foundStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/students/delete/{studentId}") //
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        userService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
