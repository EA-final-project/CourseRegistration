package registrationsystem.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
}
