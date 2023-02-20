package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.RoomTypeRestClient;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeDto;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import com.sk02.sk02_gui_service.view.manager.dialogs.RoomTypesDialog;
import com.sk02.sk02_gui_service.view.panes.RoomTypePane;
import com.sk02.sk02_gui_service.view.shared.ErrorDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class AddRoomTypeController implements EventHandler<ActionEvent> {

    private RoomTypeRestClient roomTypeRestClient;

    public AddRoomTypeController() {
        roomTypeRestClient = new RoomTypeRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String priceString = RoomTypesDialog.getInstance().getTfPrice().getText();
        String lbString = RoomTypesDialog.getInstance().getTfLowerB().getText();
        String ubString = RoomTypesDialog.getInstance().getTfUpperB().getText();
        String category = RoomTypesDialog.getInstance().getTfCategory().getText();

        if (category == null || category.isEmpty() || priceString == null || priceString.isEmpty() || lbString == null || lbString.isEmpty() || ubString == null || ubString.isEmpty()){
            new ErrorDialog("Wrong Input!").show();
            return;
        }

        double price = Double.parseDouble(priceString);
        int lowerBound = Integer.parseInt(lbString);
        int upperBound = Integer.parseInt(ubString);

        try {
            roomTypeRestClient.addRoomType(ManagerView.getInstance().getHotelDto().getId(), price, category, lowerBound, upperBound);
            RoomTypesDialog.getInstance().clean();

            List<RoomTypeDto> roomTypes = roomTypeRestClient.getRoomTypesByHotel(ManagerView.getInstance().getHotelDto().getId());

            RoomTypesDialog.getInstance().getVbRoomTypes().getChildren().clear();
            for (RoomTypeDto roomTypeDto: roomTypes){
                RoomTypesDialog.getInstance().getVbRoomTypes().getChildren().add(new RoomTypePane(roomTypeDto));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
