package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.CourseOffering;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
    @Query("update CourseOffering c set c.availableSeats = :availableSeats")
    void updateAvailableSeats(@Param("availableSeats") int    availableSeats);

}
