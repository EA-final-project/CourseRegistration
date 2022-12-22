package registrationsystem.service.dto;

import lombok.*;
import registrationsystem.domain.Address;
import registrationsystem.domain.CourseOffering;
import registrationsystem.domain.RegistrationRequest;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationDTO {
    private Long id;
    private String studentId;
    private Long courseOfferedId;
    private Collection<StudentDTO> studentDTOS;
    private Collection<CourseOfferingDTO> courseOfferingDTOS;
}
