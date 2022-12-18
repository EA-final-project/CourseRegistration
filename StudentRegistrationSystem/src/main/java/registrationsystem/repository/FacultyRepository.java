package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
