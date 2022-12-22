package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.RegistrationEvent;

import javax.transaction.Transactional;
import java.util.Collection;


@Repository
//@Transactional
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {
    RegistrationEvent findFirstByOrderByStartDateDesc();

    @Query(value = "select events " +
            "from RegistrationEvent events join events.registrationGroups groups join groups.students stu " +
            "where stu.studentId=:studentId")
    Collection<RegistrationEvent> findRegistrationEventsById(@Param("studentId") String studentId);
}
