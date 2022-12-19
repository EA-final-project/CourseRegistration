package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Course;
import registrationsystem.domain.RegistrationEvent;
import registrationsystem.domain.Student;
import registrationsystem.service.dto.CourseDTO;

import java.util.Optional;


@Repository
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {
    RegistrationEvent findFirstByOrderByStartDateAsc();

    @Query(value = "select r.groupName from registration_group r where r.id = (select a from academic_block where a.id = (select c from course_offering c where c.studentId =: studentId and c.gropName = : groupName))",nativeQuery = true)
    String readRegistrationEvent(Long studentId, String groupName);

}
