package registrationsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import registrationsystem.domain.*;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.repository.RegistrationRequestRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.RegistrationService;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConvertToRegistration {
    @Autowired
    private RegistrationService registrationService;

    public Registration convertor(Collection<RegistrationRequest> requests, Collection<CourseOffering> courseOfferings) { //fixme ---> param

        Registration registration = new Registration();

        for (RegistrationRequest request : requests) {
            if (request.getCourseOffering().getAvailableSeats() > 0) {
                //convert the RegistrationRequest to Registration
                registration = new Registration(1L, request.getStudent().getStudentId(),
                        request.getCourseOffering().getCourse().getId(), Arrays.asList(request.getStudent()),
                        Arrays.asList(request.getCourseOffering()));
                //registrationService.saveRegistration(registration);
                return registration;
            }else {
                return null;
            }
        }
        return registration;
    }

//    public Registration convertTORegistration(String studentId) {
//
//        //getting the student that we have to process his/her requests
//        Student student = registrationEventRepository.findAll().stream()
//                .flatMap(m -> m.getRegistrationGroups().stream())
//                .flatMap(s -> s.getStudents().stream())
//                .filter(stu -> stu.getStudentId().equals(studentId))
//                .findFirst()
//                .get();
//
//        //validate if student
//        if (student == null) {
//            throw new CourseExceptionHandler("Student with id : " + studentId + " not found");
//        }
//
//        //registrationEventRepository.readRegistrationEvent(studentId, ); //accept event id
//
//        Collection<RegistrationEvent> allRequest = registrationEventRepository.findAll();
//
//        Collection<AcademicBlock> allBlocks = allRequest.stream()
//                .flatMap(gr -> gr.getRegistrationGroups().stream())
//                .flatMap(block -> block.getAcademicBlocks().stream())
//                .collect(Collectors.toList());
//
//
//        Collection<Student> listStudent = new ArrayList<>();
//        Collection<CourseOffering> listOffering = new ArrayList<>();
//        Collection<RegistrationRequest> studentRequest = student.getRegistrationRequests();
//
//        Registration registration = null;
//
//        //looping for each AcademicBlock
//        for (AcademicBlock block : allBlocks) {
//            //looping each RegistrationRequest in AcademicBlock
//            for (RegistrationRequest request : studentRequest) {
//
//                //getting the CourseOffering from the that block
//                List<CourseOffering> allOffering = new ArrayList<>();
//                CourseOffering offering = request.getCourseOffering();
//                allOffering.add(offering);
//
//                int selectRandomRequest = (int) (Math.random() * (allRequest.size() + 1));
//                CourseOffering selected = allOffering.get(selectRandomRequest);
//
//                if (selected.getAvailableSeats() < selected.getCapacity()) {
//
//                    selected.calculateAvailableSeats(1);
//                    listOffering.add(offering);
//                    Course course = offering.getCourse();
//
//                    registration = new Registration(null, student.getStudentId(), course.getId(), listStudent, listOffering); //fixme --> list of students
//
//                } else {
//                    throw new CourseExceptionHandler("There is no available seats for this CourseOffer");
//                }
//            }
//        }
//        return registration;
//    }
}
