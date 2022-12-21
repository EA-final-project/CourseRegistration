package registrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDetails {

    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Long eventId;
    private String startDate;
    private String endDate;

}
