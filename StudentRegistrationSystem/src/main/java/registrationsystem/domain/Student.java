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
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne //(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //default joinColumn & eager
    private Address mailingAddress;
    @ManyToOne //(cascade = CascadeType.MERGE)
    private Address homeAddress;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "student_registrationrequest")
    private Collection<RegistrationRequest> registrationRequests;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "students")
    private Collection<RegistrationGroup> registrationGroups;
}
