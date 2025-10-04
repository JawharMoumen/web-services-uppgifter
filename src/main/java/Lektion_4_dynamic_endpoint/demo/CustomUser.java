package Lektion_4_dynamic_endpoint.demo;

public class CustomUser {
    private int id;
    private String username;

    public CustomUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getters (behövs för att skicka JSON)
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    // Setters (behövs om du tar emot data med POST)
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
