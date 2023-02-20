package com.sk02.sk02_gui_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk02.sk02_gui_service.model.User;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.clients.user.UserRestClient;
import com.sk02.sk02_gui_service.view.LoginView;
import com.sk02.sk02_gui_service.view.client.ClientView;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import com.sk02.sk02_gui_service.view.shared.ErrorDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.util.Base64;

public class LoginController implements EventHandler<ActionEvent> {

    private UserRestClient userRestClient;
    private ObjectMapper objectMapper;

    public LoginController(){
        userRestClient = new UserRestClient();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if(LoginView.getInstance().getTfEmail().getText().isEmpty() || LoginView.getInstance().getPasswordField().getText().isEmpty()){
                new ErrorDialog("Wrong input!").show();
                return;
            }

            String token = userRestClient.login(LoginView.getInstance().getTfEmail().getText(), LoginView.getInstance().getPasswordField().getText());
            UserData.getInstance().setToken(token);

            decode(token);

            if (UserData.getInstance().getRole().equals("CLIENT")){
                LoginView.getInstance().close();
                ClientView.getInstance().show();
                ClientView.getInstance().refresh();
            }
            else if (UserData.getInstance().getRole().equals("MANAGER")){
                LoginView.getInstance().close();
                ManagerView.getInstance().show();
                ManagerView.getInstance().refresh();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decode(String token){
        String tokenPayload = token.split("\\.")[1];
        String json = new String(Base64.getDecoder().decode(tokenPayload));

        try {
            User user = objectMapper.readValue(json, User.class);

            UserData.getInstance().setId(user.getId());
            UserData.getInstance().setRole(user.getRole());
            UserData.getInstance().setUsername(user.getUsername());
            UserData.getInstance().setEmail(user.getEmail());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
