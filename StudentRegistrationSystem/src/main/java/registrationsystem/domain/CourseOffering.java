package registrationsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Long capacity;
    private int availableSeats;

    public CourseOffering(String code, Long capacity, int availableSeats) {
        this.code = code;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
    }

}
