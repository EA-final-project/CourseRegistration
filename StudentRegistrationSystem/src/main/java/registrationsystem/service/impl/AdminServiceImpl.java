package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Student;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.UserService;
import registrationsystem.service.dto.RegistrationDTO;
import registrationsystem.service.dto.StudentDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminServiceImpl implements UserService {

    @Autowired
    private RegistrationRepository registrationRepository;
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
    public Collection<RegistrationDTO> listRegistrations() {
        var allList = registrationRepository.findAll();

        return allList.stream()
                .map(list -> modelMapper.map(list, RegistrationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public StudentDTO getStudent(String studentId) {
        var student = studentRepository.findStudentByStudentId(studentId);
        if (student == null) {
            System.out.println("Student with id " + studentId + " not found");
        }
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudent(String studentId, Student student) {
        var foundStudent = studentRepository.findStudentByStudentId(studentId).get();

        if (foundStudent != null) {
            foundStudent.setStudentId(student.getStudentId());
            foundStudent.setEmail(student.getEmail());
            foundStudent.setHomeAddress(student.getHomeAddress());
            foundStudent.setMailingAddress(student.getMailingAddress());
            foundStudent.setRegistrationRequests(student.getRegistrationRequests());
            studentRepository.save(foundStudent);
        }
        else {
            System.out.println("Student with id " + studentId + " not found");
        }
        return modelMapper.map(foundStudent, StudentDTO.class);
    }
}
