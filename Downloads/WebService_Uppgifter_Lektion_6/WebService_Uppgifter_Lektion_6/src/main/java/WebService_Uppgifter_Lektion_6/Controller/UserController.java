package WebService_Uppgifter_Lektion_6.Controller;

import WebService_Uppgifter_Lektion_6.model.User;
import WebService_Uppgifter_Lektion_6.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Användare med ID " + id + " hittades inte"));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            return ResponseEntity.badRequest().body("Användarnamn saknas");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Lösenord saknas");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Användarnamn är redan taget");
        }

        if (user.getIsEnabled() == null) {
            user.setIsEnabled(true);
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Användare med ID " + id + " existerar inte");
        }

        if (updatedUser.getUsername() == null || updatedUser.getUsername().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Användarnamn får inte vara tomt");
        }

        if (updatedUser.getPassword() == null || updatedUser.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Lösenord får inte vara tomt");
        }

        User existingUser = userRepository.findById(id).get();

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());

        if (updatedUser.getIsEnabled() != null) {
            existingUser.setIsEnabled(updatedUser.getIsEnabled());
        }

        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Användare med ID " + id + " hittades inte");
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("Användare med ID " + id + " har tagits bort");
    }
}