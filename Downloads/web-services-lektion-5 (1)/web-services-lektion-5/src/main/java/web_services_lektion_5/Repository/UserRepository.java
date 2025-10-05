package web_services_lektion_5.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import web_services_lektion_5.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}


