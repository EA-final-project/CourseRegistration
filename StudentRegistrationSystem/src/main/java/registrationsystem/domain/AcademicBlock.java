package registrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor

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
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;
    @JsonManagedReference
    @OneToMany(mappedBy = "academicBlocks",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<CourseOffering> courseOfferings;

}
