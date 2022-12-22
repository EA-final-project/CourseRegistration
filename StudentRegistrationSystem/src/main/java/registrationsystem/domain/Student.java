package registrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
@Entity
public class Student {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne //(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //default joinColumn & eager
    private Address mailingAddress;
    @ManyToOne //(cascade = CascadeType.MERGE)
    private Address homeAddress;
    //@JsonManagedReference
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "student")
    private Collection<RegistrationRequest> registrationRequests;
    @JsonBackReference
    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "students")
    private Collection<RegistrationGroup> registrationGroups;
}
