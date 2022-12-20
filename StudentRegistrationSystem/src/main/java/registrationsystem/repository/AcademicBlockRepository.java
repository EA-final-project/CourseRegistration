package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.AcademicBlock;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AcademicBlockRepository extends JpaRepository<AcademicBlock, Long> {
}
