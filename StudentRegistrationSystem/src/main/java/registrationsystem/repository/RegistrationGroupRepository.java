package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.RegistrationGroup;

@Repository
public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup,Long> {
}
