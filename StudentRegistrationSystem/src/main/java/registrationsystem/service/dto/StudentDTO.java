package registrationsystem.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registrationsystem.domain.Address;
import registrationsystem.domain.RegistrationRequest;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String studentId;
    private String name;
    private String email;
    private Address mailingAddress;
    @ManyToOne
    private Address homeAddress;
    @OneToMany
    private Collection<RegistrationRequest> registrationRequests;
}
