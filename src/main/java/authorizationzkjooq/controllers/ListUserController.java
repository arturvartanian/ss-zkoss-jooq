package authorizationzkjooq.controllers;

import authorizationzkjooq.model.service.impls.UserServiceImpl;
import authorizationzkjooq.model.service.interfaces.UserService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;



public class ListUserController extends SelectorComposer<Component> {
    @Wire
    private Listbox userListBox;

    private UserService userService = new UserServiceImpl();

    @Listen("onClick=#getUsers; onOK=#success")
    public void getUsers(){
        userListBox.setModel(new ListModelList<>(userService.getAllUsers()));
    }
}
