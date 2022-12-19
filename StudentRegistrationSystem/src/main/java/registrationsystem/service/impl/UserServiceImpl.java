package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Registration;
import registrationsystem.domain.Student;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.UserService;
import registrationsystem.service.dto.RegistrationDTO;
import registrationsystem.service.dto.StudentDTO;
import registrationsystem.util.ConvertToRegistration;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ConvertToRegistration convertToRegistration;
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
        if (student == null) {
            throw new CourseExceptionHandler("Student with id " + studentId + " not found");
        }
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudent(Long studentId, Student student) {
        var foundStudent = studentRepository.findStudentByStudentId(studentId).get();

        if (foundStudent != null) {
            foundStudent.setStudentId(student.getStudentId());
            foundStudent.setEmail(student.getEmail());
            foundStudent.setHomeAddress(student.getHomeAddress());
            foundStudent.setMailingAddress(student.getMailingAddress());
            foundStudent.setRegistrationRequests(student.getRegistrationRequests());
            studentRepository.save(foundStudent);
        } else {
            throw new CourseExceptionHandler("Student with id " + studentId + " not found");
        }
        return modelMapper.map(foundStudent, StudentDTO.class);
    }

    @Override
    public String readRegistrationEvent(Long studentId, String groupName) {
        var registered = registrationEventRepository.readRegistrationEvent(studentId, groupName);
        return registered;
    }

    @Override
    public RegistrationDTO processRegistrationRequest(Long id, boolean isAdmin) {

        var toProcess = studentRepository.findById(id);

        if (toProcess == null) {
            throw new CourseExceptionHandler("Student with id: " + id + " not found");
        }
        if (isAdmin == false) {
            throw new CourseExceptionHandler("YOU ARE NOT ALLOWED TO PROCESS the Request");
        }

        //Registration registration = convertToRegistration.convertTORegistration(id);

        Registration registration = new Registration(); //fixme ------>
        return modelMapper.map(registration, RegistrationDTO.class);
    }
}
