package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.CourseOffering;
import registrationsystem.service.CourseOfferingService;

@RestController
@RequestMapping("/course-offers")
public class CourseOfferingController {

    @Autowired
    private CourseOfferingService service;

    @PostMapping
    public ResponseEntity<?> addCourseOffer(@RequestBody CourseOffering course){
        service.addCourseOffer(course);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseOffer(@PathVariable Long id){
        var course = service.getCourseOffer(id);
        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }
    @GetMapping
    public ResponseEntity<?> getAllCourseOffers(){
        var allOffers = service.getAllCourseOffers();
        return ResponseEntity.ok(allOffers);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourseOffer(@PathVariable Long id, @RequestBody CourseOffering course){
        var update = service.updateCourseOffer(id,course);
        if(update == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourseOffer(@PathVariable Long id){
        service.deleteCourseOffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
