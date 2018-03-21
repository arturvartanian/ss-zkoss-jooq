package authorizationzkjooq.model.dao.interfaces;

import authorizationzkjooq.model.domain.User;
import org.jooq.exception.DataAccessException;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserByEmail(String email);

    void addUser(User user);

}
