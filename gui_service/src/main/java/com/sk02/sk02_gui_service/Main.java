package com.sk02.sk02_gui_service;

import com.sk02.sk02_gui_service.view.LoginView;
import com.sk02.sk02_gui_service.view.RegisterView;
import com.sk02.sk02_gui_service.view.client.ClientView;
import com.sk02.sk02_gui_service.view.client.dialogs.EditProfileClientDialog;
import com.sk02.sk02_gui_service.view.client.dialogs.ReservationDialog;
import com.sk02.sk02_gui_service.view.client.dialogs.ReservationsClientDialog;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.AddReviewDialog;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.EditReviewDialog;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.MyReviewsDialog;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import com.sk02.sk02_gui_service.view.manager.dialogs.*;
import com.sk02.sk02_gui_service.view.shared.NotificationsDialog;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        LoginView.getInstance().show();
//        RegisterView.getInstance().show();
//        new EditHotelDialog().show();
//        new EditRoomTypeDialog().show();
//        new AddReviewDialog().show();
//        new EditReviewDialog().show();
//        new MyReviewsDialog().show();
//        new EditProfileClientDialog().show();
//        new EditProfileManagerDialog().show();
//        new ReservationDialog().show();
//        new ReservationsClientDialog().show();
//        new ReservationsManagerDialog().show();
//        new NotificationsDialog().show();
//        ClientView.getInstance().show();
//        ManagerView.getInstance().show();
//        new RoomTypesDialog().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
