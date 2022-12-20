package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.RegistrationRequest;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
}
