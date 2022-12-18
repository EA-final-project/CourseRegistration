package registrationsystem.service;

import registrationsystem.domain.Course;
import registrationsystem.service.dto.CourseDTO;

import java.util.Collection;

public interface CourseService {

    void deleteCourse(Long id);
    CourseDTO getCourse(Long id);
    void addCourse(Course course);
    Collection<CourseDTO> getAllCourses();
    CourseDTO getCourseByCode(String code);
    CourseDTO updateCourse(Long id,Course course);
}
