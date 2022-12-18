package registrationsystem.service.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import registrationsystem.domain.CourseOffering;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AcademicBlockDTO {
    private Long id;

    private String code;
    private String name;
    private String semester;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Collection<CourseOffering> courseOfferings;
}
