package registrationsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import registrationsystem.domain.*;
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

    public static void main(String[] args) {
        SpringApplication.run(StudentRegistrationSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //creating address
        Address homeAddress = new Address(null, "1000 N 4th street", "Fairfield", 52557L, "IOWA", "USA");
        Address mailingAddress = new Address(null, "345 S avenue ", "Chicago", 21243L, "IL", "USA");
        addressRepository.save(homeAddress);
        addressRepository.save(mailingAddress);

        //creating course
        Course course1 = new Course(null, "CS590", "SA", "Software Architecture");
        Course course2 = new Course(null, "CS544", "EA", "Enterprise Architecture");
        Course course3 = new Course(null, "CS477", "WAP", "Web Application Programming");


        Faculty faculty1 = new Faculty(null, "Renee", "De Joe", "renee@miueedu", "Professor");
        Faculty faculty2 = new Faculty(null, "Payman", "Salek", "peyman@miueedu", "Professor");
        Faculty faculty3 = new Faculty(null, "Bruce", "Laster", "bruce@miueedu", "Professor");

        //creating courseOffering
        Collection<CourseOffering> listOffering = new ArrayList<>();
        CourseOffering courseOffering1 = new CourseOffering(null, "CS590", 30, 30, faculty1, course1, null);
        courseOffering1.setInitial(courseOffering1.getFaculty().getFistName().charAt(0)+"."+courseOffering1.getFaculty().getLastName().charAt(0));
        CourseOffering courseOffering2 = new CourseOffering(null, "CS544", 30, 30, faculty2, course2, null);
        courseOffering2.setInitial(courseOffering2.getFaculty().getFistName().charAt(0)+"."+courseOffering2.getFaculty().getLastName().charAt(0));
        CourseOffering courseOffering3 = new CourseOffering(null, "CS477", 30, 30, faculty3, course3, null);
        courseOffering3.setInitial(courseOffering3.getFaculty().getFistName().charAt(0)+"."+courseOffering3.getFaculty().getLastName().charAt(0));
        listOffering.add(courseOffering1);
        listOffering.add(courseOffering2);
        listOffering.add(courseOffering3);

        //creating academicBlock
        Collection<AcademicBlock> listBlocks = new ArrayList<>();
        AcademicBlock academicBlock1 = new AcademicBlock(null, "CS590", "January", "Spring", LocalDate.of(2023, 01, 16), LocalDate.of(2023, 02, 06), listOffering);
        AcademicBlock academicBlock2 = new AcademicBlock(null, "CS544", "February", "Spring", LocalDate.of(2023, 02, 06), LocalDate.of(2023, 03, 06), listOffering);
        AcademicBlock academicBlock3 = new AcademicBlock(null, "CS477", "March", "Spring", LocalDate.of(2023, 03, 06), LocalDate.of(2023, 04, 06), listOffering);

        //setting the academic block
        courseOffering1.setAcademicBlock(academicBlock1);
        courseOffering2.setAcademicBlock(academicBlock2);
        courseOffering3.setAcademicBlock(academicBlock3);
        listBlocks.add(academicBlock1);
        listBlocks.add(academicBlock2);
        listBlocks.add(academicBlock3);

        //creating registrationRequest
        Collection<RegistrationRequest> listRequest = new ArrayList<>();
        RegistrationRequest registrationRequest1 = new RegistrationRequest(null, 1, courseOffering1);
        RegistrationRequest registrationRequest2 = new RegistrationRequest(null, 1, courseOffering2);
        RegistrationRequest registrationRequest3 = new RegistrationRequest(null, 1, courseOffering3);
        listRequest.add(registrationRequest1);
        listRequest.add(registrationRequest2);
        listRequest.add(registrationRequest3);

        //creating FPP
        // Collection<RegistrationGroup> fppGroups = new ArrayList<>();
        //RegistrationGroup fpp = new RegistrationGroup(null,listBlocks,null);

        //creating RegistrationGroup
        Collection<RegistrationGroup> registrationGroups = new ArrayList<>();
        RegistrationGroup fppGroup = new RegistrationGroup(null, listBlocks, null);
        registrationGroups.add(fppGroup);


        //creating student
        Collection<Student> fppStudents = new ArrayList<>();
        Student student1 = new Student(null, "111", "Robeil", "Aregawi", "robeilaregawi@miu.edu", mailingAddress, homeAddress, listRequest, registrationGroups);
        Student student2 = new Student(null, "222", "Serapie", "Tuyishimire", "stuyishimire@miu.edu", mailingAddress, homeAddress, listRequest, registrationGroups);
        Student student3 = new Student(null, "333", "Regaul", "Karim", "regualcse@gmail.ocm", mailingAddress, homeAddress, listRequest, registrationGroups);

        fppStudents.add(student1);
        fppStudents.add(student2);
        fppStudents.add(student3);

        //setting registrationGroup
        fppGroup.setStudents(fppStudents);

        registrationGroupRepository.save(fppGroup);

        RegistrationEvent februaryEntry = new RegistrationEvent(null,LocalDate.of(2023, 02, 10),LocalDate.of(2023, 02, 20),registrationGroups);
        RegistrationEvent aprilEntry = new RegistrationEvent(null,LocalDate.of(2023, 04, 10),LocalDate.of(2023, 04, 20),registrationGroups);
        RegistrationEvent augustEntry = new RegistrationEvent(null,LocalDate.of(2023, 8, 10),LocalDate.of(2023, 8, 20),registrationGroups);
        RegistrationEvent novemberEntry = new RegistrationEvent(null,LocalDate.of(2023, 11, 10),LocalDate.of(2023, 11, 20),registrationGroups);
        //RegistrationEvent testingEvent = new RegistrationEvent(null,LocalDate.of(2022, 12, 10),LocalDate.of(2022, 12, 15),registrationGroups);

        registrationEventRepository.save(februaryEntry);
        registrationEventRepository.save(aprilEntry);
        registrationEventRepository.save(augustEntry);
        registrationEventRepository.save(novemberEntry);
       // registrationEventRepository.save(testingEvent);

    }
}
