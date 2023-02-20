package com.sk02.sk02_gui_service.view.manager;

import com.sk02.sk02_gui_service.controller.ReservationsController;
import com.sk02.sk02_gui_service.controller.RoomTypesController;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelDto;
import com.sk02.sk02_gui_service.utils.ManagerUtils;
import com.sk02.sk02_gui_service.view.LoginView;
import com.sk02.sk02_gui_service.view.manager.dialogs.EditHotelDialog;
import com.sk02.sk02_gui_service.view.manager.dialogs.EditProfileManagerDialog;
import com.sk02.sk02_gui_service.view.shared.NotificationsDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerView extends Stage {

    private static ManagerView instance;
    private ManagerUtils utils;

    private HotelDto hotelDto;

    private Label lblName;
    private Label lblDescription;
    private Label lblCity;

    private ManagerView(){
        init();
        utils = new ManagerUtils();
    }

    public static ManagerView getInstance(){
        if(instance == null){
            instance = new ManagerView();
        }
        return instance;
    }

    private void init(){
        setTitle("GMTA Manager");

        //logout
        Button btnLogout = new Button("Logout");
        btnLogout.setMinWidth(80);
        btnLogout.getStyleClass().add("button-orange");

        btnLogout.setOnAction(actionEvent -> {
            this.close();
            UserData.getInstance().logOut();
            LoginView.getInstance().clean();
            LoginView.getInstance().show();
        });

        HBox hbTopLeft = new HBox();
        hbTopLeft.setAlignment(Pos.CENTER_LEFT);
        hbTopLeft.setPadding(new Insets(15));
        hbTopLeft.getChildren().add(btnLogout);

        //right panel
        Button btnNotifications = new Button();
        btnNotifications.setMinWidth(30);
        btnNotifications.getStyleClass().add("notification-button");
        Image image = new Image("icons/notif_icon.png");
        ImageView icon = new ImageView(image);
        btnNotifications.setGraphic(icon);

        btnNotifications.setOnAction(actionEvent -> {
            NotificationsDialog.getInstance().refresh();
            NotificationsDialog.getInstance().show();
        });

        Button btnReservations = new Button("My Reservations");
        btnReservations.setMinWidth(80);
        btnReservations.getStyleClass().add("button-blue");

        btnReservations.setOnAction(new ReservationsController());

        Button btnProfile = new Button("Edit Profile");
        btnProfile.setMinWidth(80);
        btnProfile.getStyleClass().add("button-orange");

        btnProfile.setOnAction(actionEvent -> {
            new EditProfileManagerDialog().show();
        });

        HBox hbTopRight = new HBox();
        hbTopRight.setAlignment(Pos.CENTER_RIGHT);
        hbTopRight.setPadding(new Insets(15));
        hbTopRight.setSpacing(10);
        hbTopRight.getChildren().addAll(btnNotifications, btnReservations, btnProfile);

        //top pane
        BorderPane bpTop = new BorderPane();
        bpTop.setLeft(hbTopLeft);
        bpTop.setRight(hbTopRight);

        //center
        Label lblTitle = new Label("My Hotel:");
        lblTitle.getStyleClass().add("title");

        lblName = new Label();
        lblCity = new Label();
        lblDescription = new Label();

        VBox vbCenter = new VBox();
        vbCenter.setAlignment(Pos.TOP_CENTER);
        vbCenter.setPadding(new Insets(15));
        vbCenter.getChildren().addAll(lblTitle, lblName, lblCity, lblDescription);

        //bottom
        Button btnRoomTypes = new Button("Room Types");
        btnRoomTypes.setMinWidth(100);
        btnRoomTypes.getStyleClass().add("button-orange");

        btnRoomTypes.setOnAction(new RoomTypesController());

        Button btnEditHotel = new Button("Edit Hotel");
        btnEditHotel.setMinWidth(100);
        btnEditHotel.getStyleClass().add("button-blue");

        btnEditHotel.setOnAction(actionEvent -> {
            new EditHotelDialog().show();
        });

        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setPadding(new Insets(15));
        vbButtons.setSpacing(10);
        vbButtons.getChildren().addAll(btnRoomTypes, btnEditHotel);

        //scene settings
        BorderPane bp = new BorderPane();
        bp.setTop(bpTop);
        bp.setCenter(vbCenter);
        bp.setBottom(vbButtons);

        Scene scene = new Scene(bp, 800, 350);
        setMinWidth(820);
        setMinHeight(370);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
    }

    public void refresh(){
        utils.refresh();
    }

    public void clean(){
        lblDescription.setText("");
        lblName.setText("");
        lblCity.setText("");
    }

    public Label getLblName() {
        return lblName;
    }

    public Label getLblDescription() {
        return lblDescription;
    }

    public Label getLblCity() {
        return lblCity;
    }

    public HotelDto getHotelDto() {
        return hotelDto;
    }

    public void setHotelDto(HotelDto hotelDto) {
        this.hotelDto = hotelDto;
    }
}
