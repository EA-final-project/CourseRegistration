package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.CourseOffering;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
}
