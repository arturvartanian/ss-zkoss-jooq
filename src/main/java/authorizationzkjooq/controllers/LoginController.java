package authorizationzkjooq.controllers;

import authorizationzkjooq.model.service.UserCredential;
import authorizationzkjooq.model.service.impls.AuthenticationServiceImpl;
import authorizationzkjooq.model.service.interfaces.AuthenticationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginController extends SelectorComposer<Component> {

    @Wire
    private Textbox email;
    @Wire
    private Textbox password;
    @Wire
    private Label message;

    private AuthenticationService authService = new AuthenticationServiceImpl();

    @Listen("onClick=#login; onOK=#loginWin")
    public void doLogin(){
        String emailValue = email.getValue();
        String pswrd = password.getValue();

        if(!authService.login(emailValue, pswrd)){
            message.setValue("account or password are not correct.");
            return;
        }

        Executions.sendRedirect("/");
    }
}
