package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.clients.user.UserRestClient;
import com.sk02.sk02_gui_service.view.LoginView;
import com.sk02.sk02_gui_service.view.client.ClientView;
import com.sk02.sk02_gui_service.view.client.dialogs.EditProfileClientDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditProfileClientController implements EventHandler<ActionEvent> {

    private UserRestClient userRestClient;
    private EditProfileClientDialog dialog;

    public EditProfileClientController(EditProfileClientDialog dialog){
        userRestClient = new UserRestClient();
        this.dialog = dialog;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String firstName = dialog.getTfFirstName().getText();
        String lastName = dialog.getTfLastName().getText();
        String username = dialog.getTfUsername().getText();
        String password = dialog.getTfPassword().getText();
        String email = dialog.getTfEmail().getText();
        String phone = dialog.getTfPhone().getText();
        String passportNo = dialog.getTfPassportNumber().getText();

        LocalDate localBirthday = dialog.getDpBirthday().getValue();
        Date birthday = null;
        if(localBirthday != null){
            Instant instant = Instant.from(localBirthday.atStartOfDay(ZoneId.systemDefault()));
            birthday = Date.from(instant);
        }

        try {
            userRestClient.editClient(firstName, lastName, username, password, email, phone, birthday, passportNo);

            dialog.close();
            ClientView.getInstance().close();

            UserData.getInstance().logOut();
            LoginView.getInstance().clean();
            LoginView.getInstance().show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
