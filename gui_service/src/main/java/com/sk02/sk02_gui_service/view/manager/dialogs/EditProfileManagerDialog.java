package com.sk02.sk02_gui_service.view.manager.dialogs;

import com.sk02.sk02_gui_service.controller.EditProfileManagerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditProfileManagerDialog extends Stage {

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfUsername;
    private TextField tfPassword;
    private TextField tfEmail;
    private TextField tfPhone;
    private DatePicker dpBirthday;
    private TextField tfHotelName;

    public EditProfileManagerDialog(){
        init();
    }

    private void init(){
        setTitle("Edit Profile");

        //title
        Label lblTitle = new Label("Edit Profile");
        lblTitle.getStyleClass().add("title");
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitle);

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
        tfPassword = new TextField();
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
        Label lblHotelName = new Label("Hotel Name:");
        tfHotelName = new TextField();
        tfHotelName.setMaxWidth(150);

        //main grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));
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

        gridPane.add(lblHotelName, 0, 7);
        gridPane.add(tfHotelName, 1, 7);

        //buttons
        Button btnEdit = new Button("Edit");
        btnEdit.setMinWidth(80);
        btnEdit.getStyleClass().add("button-blue");

        btnEdit.setOnAction(new EditProfileManagerController(this));

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(80);
        btnCancel.getStyleClass().add("button-orange");

        btnCancel.setOnAction(actionEvent -> {
            this.close();
        });

        HBox hbButtons = new HBox();
        hbButtons.setPadding(new Insets(10));
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnEdit, btnCancel);

        //scene settings
        BorderPane bp = new BorderPane();
        bp.setTop(hbTitle);
        bp.setCenter(gridPane);
        bp.setBottom(hbButtons);

        Scene scene = new Scene(bp, 500, 600);
        setMinWidth(510);
        setMinHeight(610);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
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

    public TextField getTfHotelName() {
        return tfHotelName;
    }
}
