package registrationsystem.service;

import registrationsystem.domain.RegistrationRequest;
import registrationsystem.domain.Student;
import registrationsystem.service.dto.RegistrationDTO;
import registrationsystem.service.dto.StudentDTO;

import java.util.Collection;

public interface UserService {

    void deleteStudent(Long id);

    void addStudent(Student student);

    StudentDTO getStudent(String studentId);

    Collection<StudentDTO> getAllStudents();

    Collection<RegistrationDTO> listRegistrations();

    StudentDTO updateStudent(String studentId, Student student);

//    String readRegistrationEvent(Long studentId, String groupName);
    //RegistrationDTO processRegistrationRequest(Long id, boolean isAdmin);
    //    StudentDetailDTO readRegistrationEvent(Long studentId, String blockName);
}
