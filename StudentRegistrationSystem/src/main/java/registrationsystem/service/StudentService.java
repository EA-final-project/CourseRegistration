package registrationsystem.service;

import registrationsystem.domain.Student;
import registrationsystem.service.dto.StudentDTO;

import java.util.Collection;

public interface StudentService {

    void deleteStudent(Long id);
    Collection<StudentDTO> getAllStudents();
    void addStudent(Student student);
    StudentDTO getStudent(Long studentId);
    StudentDTO updateStudent(Long studentId, Student student);
}
