package WebService_Uppgifter_Lektion_7.Repository;


import WebService_Uppgifter_Lektion_7.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Uppgift #5: Custom metod med Optional
    Optional<Student> findStudentById(Long id);

    // Extra metoder som kan vara användbara
    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);
}