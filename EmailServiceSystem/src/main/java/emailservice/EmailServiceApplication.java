package emailservice;

import emailservice.config.EmailSenderService;
import emailservice.domain.StudentDetails;
import emailservice.service.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories
@EnableKafka
public class EmailServiceApplication implements CommandLineRunner {

    @Autowired
    private StudentDetailsService studentDetailsService;
    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<StudentDetails> allStudentDetails = studentDetailsService.getAllAllStudents();
        String subject = "Registration Event Reminder!";
        String body = null;

        Collection<StudentDetails> kafkaStudent = new ArrayList<>();

        for(StudentDetails student: allStudentDetails){
            kafkaStudent.add(student);
        }
//        kafkaStudent.forEach(stu -> emailSenderService.sendEmail(
//                stu.getEmail(),
//                subject,
//                stu.getEventId() + " " +stu.getStartDate() +" "+stu.getEndDate()
//        ));
        emailSenderService.sendEmail("silukeen1@gmail.com","Hey there","Getting email from course registrar");
    }
}
