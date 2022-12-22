package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Course;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
//@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {
   // @Query("select c from Course c where c.code =: code")
    Optional<Course> findCourseByCode(String code);
}
