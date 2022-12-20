package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Course;
import registrationsystem.domain.RegistrationEvent;
import registrationsystem.domain.Student;
import registrationsystem.service.dto.CourseDTO;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Repository
@Transactional
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {
    RegistrationEvent findFirstByOrderByStartDateAsc();
    @Query(value = "select events " +
            "from RegistrationEvent events join events.registrationGroups groups join groups.students stu " +
            "where stu.studentId=:studentId")
    Collection<RegistrationEvent> findRegistrationEventsById(@Param("studentId") String studentId);
}
