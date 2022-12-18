package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.Faculty;
import registrationsystem.service.FacultyService;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @PostMapping
    private ResponseEntity<?> addFaculty(@RequestBody Faculty faculty) {
        facultyService.addFaculty(faculty);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFaculty(@PathVariable Long id) {
        var faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<?> getAllFaculties() {
        var listFaculties = facultyService.getAllFaculty();
        return ResponseEntity.ok(listFaculties);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        var updateFaculty = facultyService.updateFaculty(id, faculty);

        if (updateFaculty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
