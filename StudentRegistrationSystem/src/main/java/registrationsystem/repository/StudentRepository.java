package registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import registrationsystem.domain.Student;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
    //@Query("select s from Student s where s.studentId=:studentId")
    Optional<Student> findStudentByStudentId(@Param("studentId") String studentId);

    //Student findStudentByStudentId(String studentId);

}
