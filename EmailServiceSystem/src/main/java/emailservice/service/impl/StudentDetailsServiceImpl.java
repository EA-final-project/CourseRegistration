package emailservice.service.impl;

import emailservice.config.EmailSenderService;
import emailservice.domain.StudentDetails;
import emailservice.repository.StudentDetailsRepository;
import emailservice.service.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendReminder() {
        var allStudents = studentDetailsRepository.findAll();
        LocalDateTime timeNow = LocalDateTime.now();

        for (StudentDetails stu : allStudents) {
            if (stu.getStartDate().isAfter(LocalDate.now())) {
                int scheduledDateTime = stu.getStartDate().atStartOfDay().getHour();
                if (scheduledDateTime < 8) {
                    emailSenderService.sendEmail(
                            stu.getEmail(),
                            "Registration reminder ",
                            "Dear Student your next registration is open in 8 hours and will open from stu.getStartDate().toString() till stu.getLastName().toString()"
                            );
                }
            }
        }
    }

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
