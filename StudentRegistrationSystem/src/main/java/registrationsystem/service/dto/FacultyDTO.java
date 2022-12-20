package registrationsystem.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class FacultyDTO {
    private Long id;
    private String fistName;
    private String lastName;
    private String email;
    private String title;
}
