package authorizationzkjooq.model.service.impls;

import authorizationzkjooq.model.service.UserCredential;
import authorizationzkjooq.model.domain.User;
import authorizationzkjooq.model.service.SessionAttributes;
import authorizationzkjooq.model.service.interfaces.AuthenticationService;
import authorizationzkjooq.model.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

    private UserService userService = new UserServiceImpl();

    public UserCredential getUserCredential(){
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(SessionAttributes.USER_CREDENTIAL);
        if(cre==null){
            cre = new UserCredential();
            sess.setAttribute(SessionAttributes.USER_CREDENTIAL, cre);
        }
        return cre;
    }

    public boolean login(String email, String pswrd) {
        User user = userService.findUser(email);

        if(user.getId() == null || !userService.verifyCredentials(user, pswrd)){
            return false;
        }

        Session sess = Sessions.getCurrent();
        UserCredential cre = new UserCredential(user.getEmail());

        sess.setAttribute(SessionAttributes.USER_CREDENTIAL,cre);

        return true;
    }

    public void logout() {
        Session session = Sessions.getCurrent();
        session.removeAttribute(SessionAttributes.USER_CREDENTIAL);
    }

    @Override
    public boolean signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);

        String salt = Password.getNextSalt();
        user.setSalt(salt);
        user.setPassword(Password.hash(password, salt));

        userService.saveUser(user);
        return true;
    }
}
