package registrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "courseoffering")
public class CourseOffering {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private int capacity;
    private int availableSeats;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Faculty faculty;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Course course;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "academicblock_courseoffering")
    private AcademicBlock academicBlock;

    private String initial;

    public CourseOffering(Long id,String code, int capacity, int availableSeats, Faculty faculty, Course course, AcademicBlock academicBlock) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
        this.faculty = faculty;
        this.course = course;
        this.academicBlock = academicBlock;
    }

    public int calculateAvailableSeats(int number) {
        return availableSeats = capacity - number;
    }

}
