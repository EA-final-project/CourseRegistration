package registrationsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationGroup {
    private List<AcademicBlock> academicBlocks;

}
