package registrationsystem.service.dto;

import lombok.*;
import registrationsystem.domain.CourseOffering;
import registrationsystem.domain.Student;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationRequestDTO {
    private Long id;
    private int priorityNumber;
    private CourseOffering courseOffering;
}
