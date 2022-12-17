package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
