package registrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class Registration {

    private List<Student> students;
    private List<CourseOffering> courseOfferings;

    public Registration(List<Student> students, List<CourseOffering> courseOfferings) {
        this.students = students;
        this.courseOfferings = courseOfferings;
    }
}
