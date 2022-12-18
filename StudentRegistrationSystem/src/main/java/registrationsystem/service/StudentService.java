package registrationsystem.service;

import registrationsystem.domain.Registration;
import registrationsystem.domain.Student;
import registrationsystem.service.dto.CourseDTO;
import registrationsystem.service.dto.RegistrationDTO;
import registrationsystem.service.dto.StudentDTO;
import registrationsystem.service.dto.StudentDetailDTO;

import java.util.Collection;

public interface StudentService {

    void deleteStudent(Long id);

    void addStudent(Student student);

    StudentDTO getStudent(Long studentId);
    Collection<StudentDTO> getAllStudents();
    Collection<RegistrationDTO> listRegistration();
    StudentDTO updateStudent(Long studentId, Student student);
    String readRegistrationEvent(Long studentId, String groupName);
//    StudentDetailDTO readRegistrationEvent(Long studentId, String blockName);
}
