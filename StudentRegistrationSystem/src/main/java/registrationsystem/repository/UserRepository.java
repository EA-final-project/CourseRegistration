package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.User;

import javax.transaction.Transactional;

@Repository
//@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
