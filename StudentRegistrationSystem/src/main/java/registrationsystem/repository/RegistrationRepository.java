package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Course;
import registrationsystem.domain.Registration;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
