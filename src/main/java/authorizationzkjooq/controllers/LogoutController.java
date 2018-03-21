package authorizationzkjooq.controllers;

import authorizationzkjooq.model.service.impls.AuthenticationServiceImpl;
import authorizationzkjooq.model.service.interfaces.AuthenticationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class LogoutController extends SelectorComposer<Component> {
    private AuthenticationService authService = new AuthenticationServiceImpl();

    @Listen("onClick=#logout")
    public void doLogout(){
        authService.logout();
        Executions.sendRedirect("/");
    }
}
