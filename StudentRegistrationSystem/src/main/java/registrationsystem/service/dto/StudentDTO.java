package registrationsystem.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registrationsystem.domain.Address;
import registrationsystem.domain.RegistrationGroup;
import registrationsystem.domain.RegistrationRequest;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Address mailingAddress;
    private Address homeAddress;
    private Collection<RegistrationRequest> registrationRequests;
    private Collection<RegistrationGroup> registrationGroups;
}
