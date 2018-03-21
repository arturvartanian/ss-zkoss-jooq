package authorizationzkjooq.model.service.interfaces;

import authorizationzkjooq.model.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User findUser(String name);

    void saveUser(User user);

    boolean verifyCredentials(User user, String password);
}
