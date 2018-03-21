package authorizationzkjooq.model.service.impls;

import authorizationzkjooq.model.dao.UserDaoImpl;
import authorizationzkjooq.model.dao.interfaces.UserDAO;
import authorizationzkjooq.model.domain.User;
import authorizationzkjooq.model.service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService{


    private UserDAO userDAO = new UserDaoImpl();

    public List<User> getAllUsers() {return userDAO.getAllUsers();}

    public User findUser(String name) {
        return userDAO.getUserByEmail(name);
    }

    public void saveUser(User user) {
        userDAO.addUser(user);
    }

    public boolean verifyCredentials(User user, String password) {
        return Password.isExpectedPassword(password, user.getSalt(), user.getPassword());
    }
}
