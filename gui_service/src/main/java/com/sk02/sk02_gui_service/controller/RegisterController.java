package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.user.UserRestClient;
import com.sk02.sk02_gui_service.view.LoginView;
import com.sk02.sk02_gui_service.view.RegisterView;
import com.sk02.sk02_gui_service.view.client.ClientView;
import com.sk02.sk02_gui_service.view.shared.ErrorDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class RegisterController implements EventHandler<ActionEvent> {

    private UserRestClient userRestClient;

    public RegisterController(){
        userRestClient = new UserRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String firstName = RegisterView.getInstance().getTfFirstName().getText();
        String lastName = RegisterView.getInstance().getTfLastName().getText();
        String username = RegisterView.getInstance().getTfUsername().getText();
        String password = RegisterView.getInstance().getTfPassword().getText();
        String email = RegisterView.getInstance().getTfEmail().getText();
        String phone = RegisterView.getInstance().getTfPhone().getText();

        LocalDate localDate = RegisterView.getInstance().getDpBirthday().getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date birthday = Date.from(instant);
        System.out.println(birthday);

        String shared = RegisterView.getInstance().getTfUserSensitive().getText();

        if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() || shared.isEmpty()){
            new ErrorDialog("Wrong Input!").show();
            return;
        }

        try {
            if (RegisterView.getInstance().getRadioClient().isSelected()){
                userRestClient.registerClient(firstName, lastName, username, password, email, phone, birthday, shared);
            }
            else if (RegisterView.getInstance().getRadioManager().isSelected()){
                userRestClient.registerManager(firstName, lastName, username, password, email, phone, birthday, shared);
            }

            RegisterView.getInstance().close();
            RegisterView.getInstance().clean();
            LoginView.getInstance().clean();
            LoginView.getInstance().show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
