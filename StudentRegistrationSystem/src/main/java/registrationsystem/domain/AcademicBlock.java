package registrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "academicblock")
public class AcademicBlock {

    @Id
    private Long id;

    private String code;
    private String name;
    private String semester;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @JsonManagedReference
    @OneToMany(mappedBy = "academicBlock",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<CourseOffering> courseOfferings = new ArrayList<>();

}
