package registrationsystem.service.dto;

import lombok.*;
import registrationsystem.domain.Course;
import registrationsystem.domain.Faculty;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CourseOfferingDTO {
    private Long id;
    private String code;
    private int capacity;
    private int availableSeats;
    private Faculty faculty;
    private Course course;
}
