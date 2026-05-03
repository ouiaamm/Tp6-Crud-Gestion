package dao;

import java.util.HashMap;
import java.util.Map;
import model.User;

public class UserImpl implements UserDAO {
    private Map<String, User> users = new HashMap<>();
    
    public void init() {
        // Ajouter l'admin par dÃ©faut
        users.put("admin", new User("admin", "admin", "ADMIN"));
        // Ajouter un utilisateur normal par dÃ©faut
        users.put("user", new User("user", "user", "USER"));
        System.out.println("User database initialized!");
    }
    
    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
    
    public boolean userExists(String username) {
        return users.containsKey(username);
    }
    
    public Map<String, User> getAllUsers() {
        return users;
    }
}
