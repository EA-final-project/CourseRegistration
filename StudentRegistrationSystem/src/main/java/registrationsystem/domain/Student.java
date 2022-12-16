package registrationsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address mailingAddress;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address homeAddress;

    public Student(String name, String email, Address mailingAddress, Address homeAddress) {
        this.name = name;
        this.email = email;
        this.mailingAddress = mailingAddress;
        this.homeAddress = homeAddress;
    }
}
