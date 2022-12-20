package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Faculty;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
