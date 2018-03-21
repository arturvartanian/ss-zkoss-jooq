package authorizationzkjooq.controllers.initiators;

import authorizationzkjooq.model.service.UserCredential;
import authorizationzkjooq.model.service.impls.AuthenticationServiceImpl;
import authorizationzkjooq.model.service.interfaces.AuthenticationService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import java.util.Map;

public class AuthenticationInit implements Initiator {

    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    public void doInit(Page page, Map<String, Object> map) {
        UserCredential cre = authenticationService.getUserCredential();
        if(cre==null || cre.isAnonymous()){
            Executions.sendRedirect("/views/login.zul");
        }
    }
}
