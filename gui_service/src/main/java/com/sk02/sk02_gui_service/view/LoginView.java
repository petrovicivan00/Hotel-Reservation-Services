package com.sk02.sk02_gui_service.view;

import com.sk02.sk02_gui_service.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends Stage {

    private static LoginView instance;

    private TextField tfEmail;
    private PasswordField passwordField;

    private LoginView(){
        init();
    }

    public static LoginView getInstance(){
        if(instance == null){
            instance = new LoginView();
        }
        return instance;
    }

    private void init(){
        setTitle("Login");

        //email
        Label lblEmail = new Label("Email:");
        tfEmail = new TextField();
        tfEmail.setMaxWidth(200);

        //password
        Label lblPassword = new Label("Password:");
        passwordField = new PasswordField();
        passwordField.setMaxWidth(200);

        Label lblNotRegistered = new Label("Not registered? Click here.");
        lblNotRegistered.getStyleClass().add("registration-text");
        lblNotRegistered.setOnMouseClicked(mouseEvent -> {
            this.close();
            RegisterView.getInstance().show();
        });

        //credentials info vbox
        VBox vbLabels = new VBox();
        vbLabels.setAlignment(Pos.CENTER_LEFT);
        vbLabels.setSpacing(10);
        vbLabels.setPadding(new Insets(20));
        vbLabels.getChildren().addAll(lblEmail, lblPassword);

        //fields vbox
        VBox vbFields = new VBox();
        vbFields.setAlignment(Pos.CENTER_LEFT);
        vbFields.setSpacing(10);
        vbFields.setPadding(new Insets(20));
        vbFields.getChildren().addAll(tfEmail, passwordField);

        //main hbox
        HBox hbMain = new HBox();
        hbMain.setAlignment(Pos.CENTER);
        hbMain.setSpacing(10);
        hbMain.setPadding(new Insets(20));
        hbMain.getChildren().addAll(vbLabels, vbFields);

        //login button
        Button btnLogin = new Button("Login");
        btnLogin.setMinWidth(120);
        btnLogin.getStyleClass().add("button-blue");

        btnLogin.setOnAction(new LoginController());

        VBox vbButtonLogin = new VBox();
        vbButtonLogin.setAlignment(Pos.CENTER);
        vbButtonLogin.setPadding(new Insets(15));
        vbButtonLogin.getChildren().addAll(btnLogin, lblNotRegistered);

        //scene settings
        BorderPane bp = new BorderPane();
        bp.setCenter(hbMain);
        bp.setBottom(vbButtonLogin);

        Scene scene = new Scene(bp, 500, 270);
        setMinWidth(510);
        setMinHeight(280);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
    }

    public void clean(){
        tfEmail.clear();
        passwordField.clear();
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }
}
