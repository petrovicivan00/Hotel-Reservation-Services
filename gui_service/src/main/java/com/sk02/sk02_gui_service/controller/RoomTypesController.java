package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.RoomTypeRestClient;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelDto;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeDto;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import com.sk02.sk02_gui_service.view.manager.dialogs.RoomTypesDialog;
import com.sk02.sk02_gui_service.view.panes.RoomTypePane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class RoomTypesController implements EventHandler<ActionEvent> {


    private RoomTypeRestClient roomTypeRestClient;

    public RoomTypesController() {
        roomTypeRestClient = new RoomTypeRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            List<RoomTypeDto> roomTypes = roomTypeRestClient.getRoomTypesByHotel(ManagerView.getInstance().getHotelDto().getId());

            RoomTypesDialog.getInstance().getVbRoomTypes().getChildren().clear();
            for (RoomTypeDto roomTypeDto: roomTypes){
                RoomTypesDialog.getInstance().getVbRoomTypes().getChildren().add(new RoomTypePane(roomTypeDto));
            }
            RoomTypesDialog.getInstance().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
