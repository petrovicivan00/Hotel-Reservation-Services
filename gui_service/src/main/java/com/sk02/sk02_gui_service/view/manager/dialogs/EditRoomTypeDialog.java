package com.sk02.sk02_gui_service.view.manager.dialogs;

import com.sk02.sk02_gui_service.controller.EditRoomTypeController;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeDto;
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

public class EditRoomTypeDialog extends Stage {

    private RoomTypeDto roomTypeDto;

    private TextField tfPrice;
    private TextField tfCategory;
    private TextField tfBoundL;
    private TextField tfBoundU;

    public EditRoomTypeDialog(RoomTypeDto roomTypeDto){
        this.roomTypeDto = roomTypeDto;
        init();
    }

    private void init(){
        setTitle("Edit Room Type");

        //title
        Label lblTitle = new Label("Edit Room Type");
        lblTitle.getStyleClass().add("title");
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitle);

        //price
        Label lblPrice = new Label("Price:");
        tfPrice = new TextField();
        tfPrice.setMaxWidth(100);

        //category
        Label lblCategory = new Label("Category:");
        tfCategory = new TextField();
        tfCategory.setMaxWidth(100);

        //bounds
        Label lblBoundL = new Label("Lower Bound:");
        tfBoundL = new TextField();
        tfBoundL.setMaxWidth(100);
        Label lblBoundU = new Label("Upper Bound:");
        tfBoundU = new TextField();
        tfBoundU.setMaxWidth(100);

        //credentials grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(lblPrice, 0, 0);
        gridPane.add(tfPrice, 1, 0);
        gridPane.add(lblCategory, 0, 1);
        gridPane.add(tfCategory, 1, 1);
        gridPane.add(lblBoundL, 0, 2);
        gridPane.add(tfBoundL, 1, 2);
        gridPane.add(lblBoundU, 0, 3);
        gridPane.add(tfBoundU, 1, 3);

        //buttons
        Button btnEdit = new Button("Edit");
        btnEdit.setMinWidth(80);
        btnEdit.getStyleClass().add("button-blue");

        btnEdit.setOnAction(new EditRoomTypeController(this));

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

        Scene scene = new Scene(bp, 500, 340);
        setMinWidth(510);
        setMinHeight(350);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
    }

    public TextField getTfPrice() {
        return tfPrice;
    }

    public TextField getTfCategory() {
        return tfCategory;
    }

    public TextField getTfBoundL() {
        return tfBoundL;
    }

    public TextField getTfBoundU() {
        return tfBoundU;
    }

    public RoomTypeDto getRoomTypeDto() {
        return roomTypeDto;
    }
}
