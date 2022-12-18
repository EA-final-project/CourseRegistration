package registrationsystem.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.RegistrationRequest;
import registrationsystem.domain.Student;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.UserService;
import registrationsystem.service.dto.RegistrationDTO;
import registrationsystem.service.dto.StudentDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationEventRepository registrationEventRepository;

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
    public Collection<RegistrationDTO> listRegistration() {
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

    @Override
    public String readRegistrationEvent(Long studentId, String groupName) {
        var registered = registrationEventRepository.readRegistrationEvent(studentId,groupName);
        return registered;
    }

    @Override
    public RegistrationDTO processRegistrationRequest(Long id, boolean isAdmin) {
        /**
         * Todo ----------------------->
         */
        return null;
    }
}
