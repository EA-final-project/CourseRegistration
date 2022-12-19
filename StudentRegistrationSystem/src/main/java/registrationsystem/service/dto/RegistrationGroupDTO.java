package registrationsystem.service.dto;

import lombok.*;
import registrationsystem.domain.AcademicBlock;
import registrationsystem.domain.Student;


import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationGroupDTO {
    private Long id;
    private String groupName;
    private Collection<AcademicBlock> academicBlocks;
    private Collection<Student> students;
}
