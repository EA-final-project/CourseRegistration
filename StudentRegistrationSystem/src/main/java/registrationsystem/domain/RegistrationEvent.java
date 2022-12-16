package registrationsystem.domain;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationEvent {

    private LocalDate startDate;
    private LocalDate endDate;

    private List<RegistrationGroup> registrationGroups;

    public RegistrationEvent(LocalDate endDate, List<RegistrationGroup> registrationGroups) {
        this.endDate = endDate;
        this.registrationGroups = registrationGroups;
    }
}
