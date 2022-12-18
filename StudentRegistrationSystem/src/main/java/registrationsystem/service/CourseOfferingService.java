package registrationsystem.service;

import registrationsystem.domain.CourseOffering;
import registrationsystem.service.dto.CourseOfferingDTO;

import java.util.Collection;

public interface CourseOfferingService {

    void deleteCourseOffer(Long id);
    CourseOfferingDTO getCourseOffer(Long id);
    Collection<CourseOfferingDTO> getAllCourseOffers();
    void addCourseOffer(CourseOffering courseOffering);
    CourseOfferingDTO updateCourseOffer(Long id,CourseOffering courseOffering);

}
