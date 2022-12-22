package registrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor

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
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "academicblock_courseoffering")
    private AcademicBlock academicBlocks;

    private String initial;

    public CourseOffering(Long id,String code, int capacity, int availableSeats, Faculty faculty, Course course, AcademicBlock academicBlocks) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
        this.faculty = faculty;
        this.course = course;
        this.academicBlocks = academicBlocks;
    }

    public int calculateAvailableSeats(int number) {
        return availableSeats = capacity - number;
    }

}
