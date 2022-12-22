package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.*;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.kafkaSender.KafkaSenderService;
import registrationsystem.repository.CourseOfferingRepository;
import registrationsystem.repository.RegistrationEventRepository;
import registrationsystem.repository.RegistrationRepository;
import registrationsystem.repository.StudentRepository;
import registrationsystem.service.RegistrationEventService;
import registrationsystem.service.RegistrationService;
import registrationsystem.service.dto.CourseOfferingDTO;
import registrationsystem.service.dto.RegistrationEventDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Override
    public void addRegistrationEvent(RegistrationEvent registrationEvent) {
        registrationEventRepository.save(registrationEvent);
    }

    @Override
    public void processRegistrationEvent(Long id, String processed) {

        var eventToProcess = registrationEventRepository.findById(id);
//        Student student = studentRepository.findById(id).get();
        //convertor
        Collection<RegistrationRequest> allRequests = new ArrayList<>();
        RegistrationStatus status = recentRegistrationEvent(id);

        RegistrationEvent recentEvent = registrationEventRepository.findFirstByOrderByStartDateDesc();

        //this event must be closed
        if (status == RegistrationStatus.CLOSED) { ///fixme ------->>>>
            System.out.println("processing the current event");

            allRequests = eventToProcess.stream()

                    .flatMap(rg -> rg.getRegistrationGroups().stream())
                    .flatMap(gr -> gr.getStudents().stream())
                    .flatMap(req -> req.getRegistrationRequests().stream())
                    .collect(Collectors.toList());

                List<Registration> reg = convertToRegistration(allRequests);
                allRequests.forEach(registration -> {
                    kafkaSenderService.sendStudentDetails("student-details", new StudentDetails(
                            registration.getStudent().getStudentId(), registration.getStudent().getFirstName(),
                            registration.getStudent().getLastName(), registration.getStudent().getEmail(),
                            eventToProcess.get().getId(), eventToProcess.get().getStartDate(),
                            eventToProcess.get().getEndDate()
                    ));
                });

                System.out.println("Converting request to registration");

        } else {
            System.out.println("The Registration Event is currently open or it is in-progress");
        }
    }
    public RegistrationStatus recentRegistrationEvent(Long id) {

        var latestEvent = registrationEventRepository.findById(id).get();

        LocalDateTime currentTime = LocalDate.now().atStartOfDay(); // 18
        LocalDateTime startDate = latestEvent.getStartDate();
        LocalDateTime endDate = latestEvent.getEndDate();

        if (currentTime.isAfter(startDate) && currentTime.isBefore(endDate)) {
            log.info("Open registration");
            return RegistrationStatus.OPEN;
        } else if (currentTime.isAfter(startDate) && currentTime.isAfter(endDate)) {
            log.info("closed registration");
            return RegistrationStatus.CLOSED;
        } else {
            log.info("In-progress registration");
            return RegistrationStatus.CLOSED;
        }
    }

    public List<Registration> convertToRegistration(Collection<RegistrationRequest> requests) { //fixme --->
        List<Registration> listRegistration = new ArrayList<>();

        for (RegistrationRequest request : requests) {
            int classCapacity = registrationRepository.findAll().size();

            if (request.getCourseOffering().getAvailableSeats() > 0) {

                request.getCourseOffering().calculateAvailableSeats(classCapacity);
                 Registration registration = new Registration(request.getId(),request.getStudent(),
                        request.getCourseOffering());

                int availableSeats = request.getCourseOffering().getAvailableSeats();

                courseOfferingRepository.updateAvailableSeats(availableSeats);
                registrationService.saveRegistration(registration);

                listRegistration.add(registration);
            } else {
                return null;
            }
        }
        return listRegistration;
    }

    @Override
    public Collection<RegistrationEventDTO> getRegistrationEventByStudentId(String studentId) {
        var allRegistrations = registrationEventRepository.findRegistrationEventsById(studentId); //working

        if (allRegistrations == null) {
            System.out.println("No registrationEvent found for student with id " + studentId + " not found");
        }
        return allRegistrations.stream()
                .map(event -> modelMapper.map(event, RegistrationEventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationEventDTO updateRegistryEvent(Long id, RegistrationEvent registrationEvent) {
        var event = registrationEventRepository.findById(id).get();
        if (event != null) {
            event.setId(registrationEvent.getId());
            event.setStartDate(registrationEvent.getStartDate());
            event.setEndDate(registrationEvent.getEndDate());
            event.setRegistrationGroups(registrationEvent.getRegistrationGroups());
            registrationEventRepository.save(event);
        } else {
            System.out.println("Registration with id " + id + " not found");
        }
        return modelMapper.map(event, RegistrationEventDTO.class);
    }

    @Override
    public RegistrationEventDTO getRegistrationEvent(Long id) {
        var event = registrationEventRepository.findById(id);
        if (event == null) {
            System.out.println("Registration with id " + id + " not found");
        }
        return modelMapper.map(event, RegistrationEventDTO.class);
    }

    @Override
    public Collection<RegistrationEventDTO> getAllRegistrationEvent() {
        var allEvents = registrationEventRepository.findAll();

        return allEvents.stream()
                .map(events -> modelMapper.map(events, RegistrationEventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationStatus recentRegistrationEvent() {

        var latestEvent = registrationEventRepository.findFirstByOrderByStartDateDesc();

        LocalDateTime currentTime = LocalDate.now().atStartOfDay(); // 18
        LocalDateTime startDate = latestEvent.getStartDate();
        LocalDateTime endDate = latestEvent.getEndDate();

        if (currentTime.isAfter(startDate) && currentTime.isBefore(endDate)) {
            log.info("Open registration");
            return RegistrationStatus.OPEN;
        } else if (currentTime.isAfter(startDate) && currentTime.isAfter(endDate)) {
            log.info("closed registration");
            return RegistrationStatus.CLOSED;
        } else {
            log.info("In-progress registration");
            return RegistrationStatus.INPROGRESS;
        }
    }

    @Override
    public void deleteRegistryEvent(Long id) {
        var event = registrationEventRepository.findById(id);

        if (event.isPresent()) {
            registrationEventRepository.deleteById(id);
        }
    }


    @Override
    public Collection<CourseOfferingDTO> readRegistrationEvent(String studentId, String track) {
        var allEvent = studentRepository.findStudentByStudentId(studentId);

        return allEvent.stream()
                .flatMap(eve -> eve.getRegistrationRequests().stream())
                .map(offer -> offer.getCourseOffering())
                .map(st -> modelMapper.map(st, CourseOfferingDTO.class))
                .collect(Collectors.toList());
    }

}
