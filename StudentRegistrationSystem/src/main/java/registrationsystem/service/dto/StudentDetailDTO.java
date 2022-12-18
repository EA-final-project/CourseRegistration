package registrationsystem.service.dto;

import lombok.*;
import registrationsystem.domain.AcademicBlock;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class StudentDetailDTO {

    private Long studentId;
    private RegistrationEventDTO eventDTO;
    private String registrationGroup;
    private AcademicBlock academicBlock;
    private CourseDTO registeredCourse;

}
