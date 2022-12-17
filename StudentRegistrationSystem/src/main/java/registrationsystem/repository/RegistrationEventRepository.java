package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.RegistrationEvent;

@Repository
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {
}
