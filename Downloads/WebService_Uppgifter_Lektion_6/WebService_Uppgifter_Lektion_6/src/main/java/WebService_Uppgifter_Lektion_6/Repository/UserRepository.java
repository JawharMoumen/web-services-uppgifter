package WebService_Uppgifter_Lektion_6.Repository;

import WebService_Uppgifter_Lektion_6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Extra metod för att hitta användare via username
    Optional<User> findByUsername(String username);

    // Extra metod för att kolla om username redan finns
    boolean existsByUsername(String username);
}