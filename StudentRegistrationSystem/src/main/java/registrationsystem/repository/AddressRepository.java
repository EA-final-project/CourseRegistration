package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Address;

import javax.transaction.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
