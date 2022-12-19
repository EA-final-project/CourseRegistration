package emailservice.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationEventDTO {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Collection<String> registrationGroups;
    /**
     * since we don't care about registration group I just change to string
     */

}
