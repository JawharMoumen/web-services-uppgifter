package Lektion_4_dynamic_endpoint.demo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> userList = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(10); // Starta på 10 eftersom vi lägger till 1-10 först

    public UserController() {
        // Initiera med users 1 till 10
        for (int i = 1; i <= 10; i++) {
            userList.add(new User(i, "User" + i));
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userList;
    }

    // Lägg till user med egen userId (inte rekommenderat, men finns med för uppgiften)
    @PostMapping("/addWithId")
    public boolean addUser(@RequestParam int userId, @RequestParam String username) {
        // Kontrollera att userId inte finns
        for (User user : userList) {
            if (user.getId() == userId) {
                throw new IllegalArgumentException("User ID " + userId + " already exists.");
            }
        }
        return userList.add(new User(userId, username));
    }

    // Auto-generate ID och lägg till user
    @PostMapping("/add")
    public User addUserAutoId(@RequestParam String username) {
        int newId = idGenerator.incrementAndGet();
        User user = new User(newId, username);
        userList.add(user);
        return user;
    }
}
