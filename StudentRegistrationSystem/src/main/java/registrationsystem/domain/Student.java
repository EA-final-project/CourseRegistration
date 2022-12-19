package registrationsystem.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;
    private String name;
    private String email;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //default joinColumn & eager
    private Address mailingAddress;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address homeAddress;
    @OneToMany
    @JoinColumn(name = "student_reg_request")
    private Collection<RegistrationRequest> registrationRequests;

}
