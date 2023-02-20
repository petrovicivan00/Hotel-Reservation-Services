package com.sk02.sk02_gui_service.view;

import com.sk02.sk02_gui_service.controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterView extends Stage {

    private static RegisterView instance;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfUsername;
    private PasswordField tfPassword;
    private TextField tfEmail;
    private TextField tfPhone;
    private DatePicker dpBirthday;

    private TextField tfUserSensitive;
    private Label lblUserSensitive;

    private RadioButton radioClient;
    private RadioButton radioManager;

    private RegisterView(){
        init();
    }

    public static RegisterView getInstance(){
        if(instance == null){
            instance = new RegisterView();
        }
        return instance;
    }

    private void init(){
        setTitle("Register");

        //hb top
        Label lblRegister = new Label("Register As:");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioClient = new RadioButton("Client");
        radioClient.setToggleGroup(toggleGroup);
        radioManager = new RadioButton("Manager");
        radioManager.setToggleGroup(toggleGroup);
        radioClient.setSelected(true);

        HBox hbTop = new HBox();
        hbTop.setPadding(new Insets(10));
        hbTop.setAlignment(Pos.CENTER);
        hbTop.setSpacing(10);
        hbTop.getChildren().addAll(lblRegister, radioClient, radioManager);

        //first name
        Label lblFirstName = new Label("First Name:");
        tfFirstName = new TextField();
        tfFirstName.setMaxWidth(150);

        //last name
        Label lblLastName = new Label("Last Name:");
        tfLastName = new TextField();
        tfLastName.setMaxWidth(150);

        //username
        Label lblUsername = new Label("Username:");
        tfUsername = new TextField();
        tfUsername.setMaxWidth(150);

        //password
        Label lblPassword = new Label("Password:");
        tfPassword = new PasswordField();
        tfPassword.setMaxWidth(150);

        //email
        Label lblEmail = new Label("Email:");
        tfEmail = new TextField();
        tfEmail.setMaxWidth(150);

        //phone
        Label lblPhone = new Label("Phone:");
        tfPhone = new TextField();
        tfPhone.setMaxWidth(150);

        //birthday
        Label lblBirthday = new Label("Birthday:");
        dpBirthday = new DatePicker();
        dpBirthday.setMaxWidth(150);

        //passport
        lblUserSensitive = new Label("Passport No:");
        tfUserSensitive = new TextField();
        tfUserSensitive.setMaxWidth(150);

        radioListeners();

        //main grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        //gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(lblFirstName, 0, 0);
        gridPane.add(tfFirstName, 1, 0);

        gridPane.add(lblLastName, 0, 1);
        gridPane.add(tfLastName, 1, 1);

        gridPane.add(lblUsername, 0, 2);
        gridPane.add(tfUsername, 1, 2);

        gridPane.add(lblPassword, 0, 3);
        gridPane.add(tfPassword, 1, 3);

        gridPane.add(lblEmail, 0, 4);
        gridPane.add(tfEmail, 1, 4);

        gridPane.add(lblPhone, 0, 5);
        gridPane.add(tfPhone, 1, 5);

        gridPane.add(lblBirthday, 0, 6);
        gridPane.add(dpBirthday, 1, 6);

        gridPane.add(lblUserSensitive, 0, 7);
        gridPane.add(tfUserSensitive, 1, 7);

        //buttons
        Button btnRegister = new Button("Register");
        btnRegister.setMinWidth(120);
        btnRegister.getStyleClass().add("button-blue");

        btnRegister.setOnAction(new RegisterController());

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(120);
        btnCancel.getStyleClass().add("button-orange");

        btnCancel.setOnAction(actionEvent -> {
            this.close();
            LoginView.getInstance().show();
            this.clean();
        });

        HBox hbButtons = new HBox();
        hbButtons.setPadding(new Insets(10));
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnRegister, btnCancel);

        //scene settings
        BorderPane bp = new BorderPane();
        bp.setTop(hbTop);
        bp.setCenter(gridPane);
        bp.setBottom(hbButtons);

        Scene scene = new Scene(bp, 600, 600);
        setMinWidth(510);
        setMinHeight(610);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
    }

    private void radioListeners(){
        radioClient.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                lblUserSensitive.setText("Passport No:");
            }
        });

        radioManager.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                lblUserSensitive.setText("Hotel Name:");
            }
        });
    }

    public void clean(){
        tfEmail.clear();
        tfFirstName.clear();
        tfPassword.clear();
        tfLastName.clear();
        tfUsername.clear();
        tfPhone.clear();
        tfUserSensitive.clear();
        dpBirthday.getEditor().clear();
    }

    public TextField getTfFirstName() {
        return tfFirstName;
    }

    public TextField getTfLastName() {
        return tfLastName;
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public TextField getTfPhone() {
        return tfPhone;
    }

    public DatePicker getDpBirthday() {
        return dpBirthday;
    }

    public TextField getTfUserSensitive() {
        return tfUserSensitive;
    }

    public Label getLblUserSensitive() {
        return lblUserSensitive;
    }

    public RadioButton getRadioClient() {
        return radioClient;
    }

    public RadioButton getRadioManager() {
        return radioManager;
    }
}
