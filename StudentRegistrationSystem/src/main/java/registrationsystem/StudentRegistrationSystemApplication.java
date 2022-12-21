package registrationsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import registrationsystem.domain.*;
import registrationsystem.kafkaSender.KafkaSenderService;
import registrationsystem.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class StudentRegistrationSystemApplication implements CommandLineRunner {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RegistrationGroupRepository registrationGroupRepository;
    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private KafkaSenderService kafkaSenderService;
    @Autowired
    private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8080/submit";
    private String requestUrl = "registration-requests/submit";

    public static void main(String[] args) {
        SpringApplication.run(StudentRegistrationSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //creating address
        Address homeAddress = new Address(1L, "1000 N 4th street", "Fairfield", 52557L, "IOWA", "USA");
        Address mailingAddress = new Address(2L, "345 S avenue ", "Chicago", 21243L, "IL", "USA");
        addressRepository.save(homeAddress);
        addressRepository.save(mailingAddress);

        //creating course
        Course course1 = new Course(1L, "CS590", "SA", "Software Architecture");
        Course course2 = new Course(2L, "CS544", "EA", "Enterprise Architecture");
        Course course3 = new Course(3L, "CS477", "WAP", "Web Application Programming");
        Course course4 = new Course(4L, "CS425", "SE", "Software Engineering");
        Course course5 = new Course(5L, "CS422", "DBMS", "Database Managements System");

        Faculty faculty1 = new Faculty(1L, "Renee", "De Joe", "renee@miueedu", "Professor");
        Faculty faculty2 = new Faculty(2L, "Payman", "Salek", "peyman@miueedu", "Professor");
        Faculty faculty3 = new Faculty(3L, "Bruce", "Laster", "bruce@miueedu", "Professor");
        Faculty faculty4 = new Faculty(4L, "Obina", "Kalu", "Obina@miueedu", "Professor");
        Faculty faculty5 = new Faculty(5L, "Joseph", "Lerman", "Joseph@miueedu", "Professor");

        //creating courseOffering
        Collection<CourseOffering> listOffering = new ArrayList<>();
        CourseOffering courseOffering1 = new CourseOffering(1L, "CS590", 30, 30, faculty1, course1, null); //fixme ----> calculate seats
        courseOffering1.setInitial(courseOffering1.getFaculty().getFistName().charAt(0) + "." + courseOffering1.getFaculty().getLastName().charAt(0));
        CourseOffering courseOffering2 = new CourseOffering(2L, "CS544", 30, 30, faculty2, course2, null);
        courseOffering2.setInitial(courseOffering2.getFaculty().getFistName().charAt(0) + "." + courseOffering2.getFaculty().getLastName().charAt(0));
        CourseOffering courseOffering3 = new CourseOffering(3L, "CS477", 30, 30, faculty3, course3, null);
        courseOffering3.setInitial(courseOffering3.getFaculty().getFistName().charAt(0) + "." + courseOffering3.getFaculty().getLastName().charAt(0));
        CourseOffering courseOffering4 = new CourseOffering(4L, "CS425", 30, 30, faculty4, course4, null);
        courseOffering4.setInitial(courseOffering4.getFaculty().getFistName().charAt(0) + "." + courseOffering4.getFaculty().getLastName().charAt(0));
        CourseOffering courseOffering5 = new CourseOffering(5L, "CS422", 30, 30, faculty5, course5, null);
        courseOffering5.setInitial(courseOffering5.getFaculty().getFistName().charAt(0) + "." + courseOffering5.getFaculty().getLastName().charAt(0));
        listOffering.add(courseOffering1);
        listOffering.add(courseOffering2);
        listOffering.add(courseOffering3);
        listOffering.add(courseOffering4);
        listOffering.add(courseOffering5);

        //creating academicBlock
        Collection<AcademicBlock> fppBlocks = new ArrayList<>();
        Collection<AcademicBlock> mppBlocks = new ArrayList<>();

        AcademicBlock academicBlock1 = new AcademicBlock(1L, "CS590", "January", "Spring", LocalDate.of(2023, 01, 16), LocalDate.of(2023, 02, 06), listOffering);
        AcademicBlock academicBlock2 = new AcademicBlock(2L, "CS544", "February", "Spring", LocalDate.of(2023, 02, 06), LocalDate.of(2023, 03, 06), listOffering);
        AcademicBlock academicBlock3 = new AcademicBlock(3L, "CS477", "March", "Spring", LocalDate.of(2023, 03, 06), LocalDate.of(2023, 04, 06), listOffering);
        AcademicBlock academicBlock4 = new AcademicBlock(4L, "CS425", "February", "Spring", LocalDate.of(2023, 02, 06), LocalDate.of(2023, 03, 06), listOffering);
        AcademicBlock academicBlock5 = new AcademicBlock(5L, "CS422", "March", "Spring", LocalDate.of(2023, 03, 06), LocalDate.of(2023, 04, 06), listOffering);

        //setting the academic block
        courseOffering1.setAcademicBlock(academicBlock1);
        courseOffering2.setAcademicBlock(academicBlock2);
        courseOffering3.setAcademicBlock(academicBlock3);
        courseOffering4.setAcademicBlock(academicBlock4);
        courseOffering5.setAcademicBlock(academicBlock5);

        fppBlocks.add(academicBlock1);
        fppBlocks.add(academicBlock2);
        fppBlocks.add(academicBlock3);
        mppBlocks.add(academicBlock4);
        mppBlocks.add(academicBlock5);

        //creating registrationRequest
        Collection<RegistrationRequest> listRequest1 = new ArrayList<>();
        Collection<RegistrationRequest> listRequest2 = new ArrayList<>();
        RegistrationRequest registrationRequest1 = new RegistrationRequest(1L, 1, null, courseOffering1);
        RegistrationRequest registrationRequest2 = new RegistrationRequest(2L, 1, null, courseOffering2);
        RegistrationRequest registrationRequest3 = new RegistrationRequest(3L, 1, null, courseOffering3);
        RegistrationRequest registrationRequest4 = new RegistrationRequest(4L, 1, null, courseOffering4);
        RegistrationRequest registrationRequest5 = new RegistrationRequest(5L, 1, null, courseOffering5);

        Collection<RegistrationRequest> listRequest3 = new ArrayList<>();
        //reguest post
        RegistrationRequest registrationRequest6 = new RegistrationRequest(6L, 1, null, courseOffering4);
        RegistrationRequest registrationRequest7 = new RegistrationRequest(6L, 1, null, courseOffering5);
        //post
        listRequest3.add(registrationRequest6);
        listRequest3.add(registrationRequest7);

        listRequest1.add(registrationRequest1);
        listRequest1.add(registrationRequest2);
        listRequest1.add(registrationRequest3);
        listRequest1.add(registrationRequest4);
        listRequest1.add(registrationRequest5);

        listRequest2.add(registrationRequest4);
        listRequest2.add(registrationRequest5);

        //creating RegistrationGroup
        Collection<RegistrationGroup> registrationGroups = new ArrayList<>();

        //creating FPP
        RegistrationGroup mppGroup = new RegistrationGroup(1L, "MPP", mppBlocks, null);
        RegistrationGroup fppGroup = new RegistrationGroup(2L, "FPP", fppBlocks, null);

        registrationGroups.add(fppGroup);
        registrationGroups.add(mppGroup);

        //creating student
        Collection<Student> fppStudents = new ArrayList<>();
        Collection<Student> mppStudents = new ArrayList<>();

        Student student1 = new Student(1L, "111", "Robeil", "Aregawi", "robeilaregawi@miu.edu", mailingAddress, homeAddress, listRequest1, registrationGroups);
        Student student2 = new Student(2L, "222", "Serapie", "Tuyishimire", "stuyishimire@miu.edu", mailingAddress, homeAddress, listRequest1, registrationGroups);
        Student student3 = new Student(3L, "333", "Regaul", "Karim", "regualcse@gmail.ocm", mailingAddress, homeAddress, listRequest1, registrationGroups);
        Student student4 = new Student(4L, "444", "Robel", "Issak", "robl@miu.edu", mailingAddress, homeAddress, listRequest1, registrationGroups);
        Student student5 = new Student(5L, "555", "Misghina", "Niguse", "misghna@gmail.ocm", mailingAddress, homeAddress, listRequest1, registrationGroups);

        registrationRequest1.setStudent(student1);
        registrationRequest2.setStudent(student2);
        registrationRequest3.setStudent(student3);
        registrationRequest4.setStudent(student4);
        registrationRequest5.setStudent(student5);

        //fpp students
        fppStudents.add(student1);
        fppStudents.add(student2);
        fppStudents.add(student3);
        //mpp students
        mppStudents.add(student4);
        mppStudents.add(student5);

        //setting registrationGroup
        fppGroup.setStudents(fppStudents);
        mppGroup.setStudents(mppStudents);

        registrationGroupRepository.save(fppGroup);
        registrationGroupRepository.save(mppGroup);

        RegistrationEvent februaryEntry = new RegistrationEvent(1L, LocalDate.of(2023, 02, 10), LocalDate.of(2023, 02, 20), registrationGroups);
        RegistrationEvent aprilEntry = new RegistrationEvent(2L, LocalDate.of(2023, 04, 10), LocalDate.of(2023, 04, 20), registrationGroups);
        RegistrationEvent augustEntry = new RegistrationEvent(3L, LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8, 20), registrationGroups);
        RegistrationEvent novemberEntry = new RegistrationEvent(4L, LocalDate.of(2023, 11, 10), LocalDate.of(2023, 11, 20), registrationGroups);
        RegistrationEvent testingEvent = new RegistrationEvent(5L, LocalDate.of(2022, 12, 10), LocalDate.of(2022, 12, 12), registrationGroups);

        registrationEventRepository.save(februaryEntry);
        registrationEventRepository.save(aprilEntry);
        registrationEventRepository.save(augustEntry);
        registrationEventRepository.save(novemberEntry);
        registrationEventRepository.save(testingEvent);

        StudentDetails details = new StudentDetails("111",
                "Robeil",
                "Aregawi",
                "silukeen1@gmail.com",
                11L,
                "2022, 02, 02",
                "2022, 04, 04");
        kafkaSenderService.sendStudentDetails("student_details1",details);

        //restTemplate.postForLocation(baseUrl+"/{studentId}",registrationRequest1,111);
        //restTemplate.postForLocation(requestUrl+"/{studentId}",listRequest3,111);
    }
}
