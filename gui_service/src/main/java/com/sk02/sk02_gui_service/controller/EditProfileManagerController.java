package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.clients.user.UserRestClient;
import com.sk02.sk02_gui_service.view.LoginView;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import com.sk02.sk02_gui_service.view.manager.dialogs.EditProfileManagerDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditProfileManagerController implements EventHandler<ActionEvent> {

    private UserRestClient userRestClient;
    private EditProfileManagerDialog dialog;

    public EditProfileManagerController(EditProfileManagerDialog editProfileManagerDialog) {
        this.dialog = editProfileManagerDialog;
        userRestClient = new UserRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String firstName = dialog.getTfFirstName().getText();
        String lastName = dialog.getTfLastName().getText();
        String username = dialog.getTfUsername().getText();
        String password = dialog.getTfPassword().getText();
        String email = dialog.getTfEmail().getText();
        String phone = dialog.getTfPhone().getText();
        String hotelName = dialog.getTfHotelName().getText();

        LocalDate localBirthday = dialog.getDpBirthday().getValue();
        Date birthday = null;
        if(localBirthday != null){
            Instant instant = Instant.from(localBirthday.atStartOfDay(ZoneId.systemDefault()));
            birthday = Date.from(instant);
        }

        try {
            userRestClient.editManager(firstName, lastName, username, password, email, phone, birthday, hotelName);

            dialog.close();
            ManagerView.getInstance().close();

            UserData.getInstance().logOut();
            LoginView.getInstance().clean();
            LoginView.getInstance().show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
