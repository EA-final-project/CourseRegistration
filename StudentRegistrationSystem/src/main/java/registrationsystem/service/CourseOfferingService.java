package registrationsystem.service;

import registrationsystem.domain.Course;
import registrationsystem.domain.CourseOffering;
import registrationsystem.domain.RegistrationRequest;
import registrationsystem.service.dto.CourseOfferingDTO;

import java.util.Collection;
import java.util.HashMap;

public interface CourseOfferingService {

    void deleteCourseOffer(Long id);
    CourseOfferingDTO getCourseOffer(Long id);
    void addCourseOffer(CourseOffering courseOffering);
    Collection<CourseOfferingDTO> getAllCourseOffers();
    CourseOfferingDTO updateCourseOffer(Long id,CourseOffering courseOffering);
   // HashMap<Boolean, Course> selectCourse(Collection<Course> listCourse, String code);

}
