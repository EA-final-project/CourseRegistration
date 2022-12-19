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
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private int capacity;
    private int availableSeats;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Faculty faculty;
    @OneToOne
    private Course course;

    public int calculateAvailableSeats(int number) {
        return availableSeats = capacity - number;
    }

}
