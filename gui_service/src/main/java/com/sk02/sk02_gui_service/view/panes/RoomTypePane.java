package com.sk02.sk02_gui_service.view.panes;

import com.sk02.sk02_gui_service.controller.DeleteRoomTypeController;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeDto;
import com.sk02.sk02_gui_service.view.manager.dialogs.EditRoomTypeDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class RoomTypePane extends VBox {

    private RoomTypeDto roomTypeDto;

    public RoomTypePane(RoomTypeDto roomTypeDto){
        this.roomTypeDto = roomTypeDto;
        init();
    }

    private void init(){
        this.getStyleClass().add("custom-pane");

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label lblCategory = new Label(roomTypeDto.getCategory());
        lblCategory.getStyleClass().add("title-pane");

        Label lblPrice = new Label(String.valueOf(roomTypeDto.getPrice()));
        Label lblBound = new Label(roomTypeDto.getLowerBound() + " - " + roomTypeDto.getUpperBound());

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.setAlignment(Pos.CENTER);

        Button btnEdit = new Button("Edit");
        btnEdit.setMinWidth(80);
        btnEdit.getStyleClass().add("button-blue");

        btnEdit.setOnAction(actionEvent -> {
            new EditRoomTypeDialog(roomTypeDto).show();
        });

        Button btnDelete = new Button("Delete");
        btnDelete.setMinWidth(80);
        btnDelete.getStyleClass().add("button-orange");

        btnDelete.setOnAction(new DeleteRoomTypeController(roomTypeDto));

        hbButtons.getChildren().addAll(btnEdit, btnDelete);

        getChildren().addAll(lblCategory, lblPrice, lblBound, hbButtons);
    }
}
