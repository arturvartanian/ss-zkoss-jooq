package authorizationzkjooq.controllers;

import authorizationzkjooq.model.service.impls.AuthenticationServiceImpl;
import authorizationzkjooq.model.service.impls.UserServiceImpl;
import authorizationzkjooq.model.service.interfaces.AuthenticationService;
import authorizationzkjooq.model.service.interfaces.UserService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController extends SelectorComposer<Component> {
    @Wire
    private Textbox email;
    @Wire
    private Textbox password;
    @Wire
    private Textbox repeatPassword;
    @Wire
    private Label message;

    private AuthenticationService authService = new AuthenticationServiceImpl();

    private UserService userService = new UserServiceImpl();

    @Listen("onClick=#signup; onOK=#successSignUp")
    public void doSignup(){
        String emailValue = email.getValue();
        String pswrd = password.getValue();

        if (verifyData() && authService.signUp(emailValue, pswrd)){
            Executions.sendRedirect("/views/login.zul");
        }
    }

    private boolean verifyData(){
        if (password.getValue().length() < 6){
            message.setValue("The password length should be at least 6 characters");
            return false;
        }
        if (!password.getValue().equals(repeatPassword.getValue())){
            message.setValue("Your passwords do not match!");
            return false;
        }
        if (!emailChecker(email.getValue())){
            message.setValue("Wrong email format!");
            return false;
        }
        if (userService.findUser(email.getValue()) != null){
            message.setValue("User with such email already exist!");
            return false;
        }

        return true;
    }


    private boolean emailChecker(String email){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
    }
}
