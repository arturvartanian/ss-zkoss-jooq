package authorizationzkjooq.model.service.interfaces;

import authorizationzkjooq.model.service.UserCredential;

public interface AuthenticationService {

    UserCredential getUserCredential();

    boolean login(String name, String password);

    void logout();

    boolean signUp(String name, String password);
}
