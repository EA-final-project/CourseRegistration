package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.RegistrationRequest;
import registrationsystem.service.RegistrationRequestService;

import java.util.Collection;

@RestController
@RequestMapping("/registration-requests")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService service;

    @PostMapping("/submit/{studentId}")
    public ResponseEntity<?> addRequest(@RequestBody Collection<RegistrationRequest> requests, @PathVariable  String studentId) {
        service.saveRegistrationRequest(requests,studentId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}") //working
    public ResponseEntity<?> getRequest(@PathVariable Long id) {
        var request = service.getRegistrationRequest(id);
        if (request == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(request);
    }

    @GetMapping //working
    public ResponseEntity<?> getAllRequests() {
        var allRequests = service.getAllRegistrationRequest();
        return ResponseEntity.ok(allRequests);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable Long id, @RequestBody RegistrationRequest request) {
        var update = service.updateRequest(id, request);
        if (update == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long id){
        var request = service.getRegistrationRequest(id);
        if(request == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(request);
    }
}
