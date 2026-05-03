package dao;

import java.util.Map;
import model.User;

public interface UserDAO {
    User authenticate(String username, String password);
    void addUser(User user);
    boolean userExists(String username);
    Map<String, User> getAllUsers();
}
