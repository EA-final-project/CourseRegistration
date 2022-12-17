package registrationsystem.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Student;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.StudentService;
import registrationsystem.service.dto.StudentDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<StudentDTO> getAllStudents() {
        var allStudent = studentRepository.findAll();

        return allStudent.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public StudentDTO getStudent(Long studentId) {
        var student = studentRepository.findStudentByStudentId(studentId);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudent(Long studentId, Student student) {
        var foundStudent = studentRepository.findStudentByStudentId(studentId).get();

        if (foundStudent != null) {
            foundStudent.setStudentId(student.getStudentId());
            foundStudent.setName(student.getName());
            foundStudent.setEmail(student.getEmail());
            foundStudent.setHomeAddress(student.getHomeAddress());
            foundStudent.setMailingAddress(student.getMailingAddress());
            foundStudent.setRegistrationRequests(student.getRegistrationRequests());
            studentRepository.save(foundStudent);
        }
        return modelMapper.map(foundStudent, StudentDTO.class);
    }
}
