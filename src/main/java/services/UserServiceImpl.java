package services;

import dao.UserDAO;
import model.User;

public class UserServiceImpl implements UserService {
    private UserDAO userDao;
    private User currentUser;
    
    public void setUserDao(UserDAO userDao) { this.userDao = userDao; }
    
    public User login(String username, String password) {
        currentUser = userDao.authenticate(username, password);
        return currentUser;
    }
    
    public boolean register(User user) {
        if (userDao.userExists(user.getUsername())) {
            return false;
        }
        userDao.addUser(user);
        return true;
    }
    
    public User getCurrentUser() { return currentUser; }
    
    public void logout() { currentUser = null; }
}
