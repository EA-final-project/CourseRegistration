package emailservice.service.impl;

import emailservice.domain.StudentDetails;
import emailservice.repository.StudentDetailsRepository;
import emailservice.service.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Override
    public StudentDetails getStudentDetails(Long id) {
        return studentDetailsRepository.findById(id).get();
    }

    @Override
    public List<StudentDetails> getAllAllStudents() {
        var allStudents = studentDetailsRepository.findAll();
        return allStudents;
    }

    @Override
    public void addStudentDetails(StudentDetails studentDetails) {
        studentDetailsRepository.save(studentDetails);
    }
}
