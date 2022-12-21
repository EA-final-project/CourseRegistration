package emailservice.service;

import emailservice.domain.StudentDetails;

import java.util.List;

public interface StudentDetailsService {

    StudentDetails getStudentDetails(Long id);
    List<StudentDetails> getAllAllStudents();
    void addStudentDetails(StudentDetails studentDetails);
}
