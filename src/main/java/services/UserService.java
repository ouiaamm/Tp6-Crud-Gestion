package services;

import model.User;

public interface UserService {
    User login(String username, String password);
    boolean register(User user);
    User getCurrentUser();
    void logout();
}
