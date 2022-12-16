package registrationsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class AcademicBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String semester;
    private LocalDate startDate;
    private LocalDate endDate;

    public AcademicBlock(String code, String name, String semester, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
