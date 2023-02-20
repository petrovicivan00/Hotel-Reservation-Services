package com.sk02.sk02_gui_service.view.manager.dialogs;

import com.sk02.sk02_gui_service.controller.EditHotelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditHotelDialog extends Stage {

    private TextField tfName;
    private TextField tfDesc;
    private TextField tfCity;

    public EditHotelDialog(){
        init();
    }

    private void init(){
        setTitle("Edit Hotel");

        //title
        Label lblTitle = new Label("Edit Hotel");
        lblTitle.getStyleClass().add("title");
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitle);

        //name
        Label lblName = new Label("Name:");
        tfName = new TextField();
        tfName.setMaxWidth(200);

        //description
        Label lblDesc = new Label("Description:");
        tfDesc = new TextField();
        tfDesc.setMaxWidth(200);

        //city
        Label lblCity = new Label("City:");
        tfCity = new TextField();
        tfCity.setMaxWidth(200);

        //credentials grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(lblName, 0, 0);
        gridPane.add(tfName, 1, 0);
        gridPane.add(lblDesc, 0, 1);
        gridPane.add(tfDesc, 1, 1);
        gridPane.add(lblCity, 0, 2);
        gridPane.add(tfCity, 1, 2);

        //buttons
        Button btnEdit = new Button("Edit");
        btnEdit.setMinWidth(80);
        btnEdit.getStyleClass().add("button-blue");

        btnEdit.setOnAction(new EditHotelController(this));

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

        Scene scene = new Scene(bp, 500, 300);
        setMinWidth(510);
        setMinHeight(310);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
    }

    public TextField getTfName() {
        return tfName;
    }

    public TextField getTfDesc() {
        return tfDesc;
    }

    public TextField getTfCity() {
        return tfCity;
    }
}
