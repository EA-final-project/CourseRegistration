package registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.Course;
import registrationsystem.service.CourseService;
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        var course = courseService.getCourse(id);
        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(courseService);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses(){
        var allCourses = courseService.getAllCourses();
        return ResponseEntity.ok(allCourses);
    }
    @GetMapping("/get/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable String code){
        var course = courseService.getCourseByCode(code);
        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course){
        var updateCourse = courseService.updateCourse(id,course);
        if(updateCourse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updateCourse);
    }
}
