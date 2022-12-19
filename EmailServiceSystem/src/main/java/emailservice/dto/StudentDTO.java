package emailservice.dto;

import jakarta.mail.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private Long studentId;
    private String name;
    private String email;
    private Address mailingAddress;
    private Address homeAddress;
    private Collection<String> registrationRequests;
    /**
     * since we don't care about registration request I  just change to string
     */
}
