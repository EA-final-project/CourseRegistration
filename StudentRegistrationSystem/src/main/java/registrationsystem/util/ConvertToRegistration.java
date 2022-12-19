package registrationsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import registrationsystem.domain.*;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ConvertToRegistration {
    @Autowired
    private StudentRepository studentRepository;

    public Registration convertTORegistration(Long id) {

        Student student = studentRepository.findById(id).get();
        Collection<Student> listStudent = new ArrayList<>();
        Collection<CourseOffering> listOffering = new ArrayList<>();

        if (student == null) {
            throw new CourseExceptionHandler("Student with id : " + id + " not found");
        }

        Registration registration = null;
        Collection<RegistrationRequest> studentRequest = student.getRegistrationRequests();

        for (RegistrationRequest request : studentRequest) {
            CourseOffering offering = request.getCourseOffering();

            if (offering.getAvailableSeats() < offering.getCapacity()) {

                offering.calculateAvailableSeats(1);
                listOffering.add(offering);
                Course course = offering.getCourse();

                registration = new Registration(null, student.getStudentId(), course.getId(), listStudent, listOffering); //fixme --> list of students

            } else {
                throw new CourseExceptionHandler("There is no available seats for this CourseOffer");
            }
        }
        return registration;
    }
}
