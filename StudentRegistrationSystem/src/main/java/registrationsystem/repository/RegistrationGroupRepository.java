package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.RegistrationGroup;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup,Long> {

}
