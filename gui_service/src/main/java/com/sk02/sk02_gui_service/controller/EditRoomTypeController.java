package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.RoomTypeRestClient;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeDto;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import com.sk02.sk02_gui_service.view.manager.dialogs.EditRoomTypeDialog;
import com.sk02.sk02_gui_service.view.manager.dialogs.RoomTypesDialog;
import com.sk02.sk02_gui_service.view.panes.RoomTypePane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class EditRoomTypeController implements EventHandler<ActionEvent> {

    private EditRoomTypeDialog dialog;
    private RoomTypeRestClient roomTypeRestClient;

    public EditRoomTypeController(EditRoomTypeDialog dialog) {
        this.dialog = dialog;
        roomTypeRestClient = new RoomTypeRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String priceString = dialog.getTfPrice().getText();
        String lbString = dialog.getTfBoundL().getText();
        String ubString = dialog.getTfBoundU().getText();
        String category = dialog.getTfCategory().getText();

        int lb = 0, ub = 0;
        double price = 0;

        if(lbString != null && !lbString.isEmpty()){
            lb = Integer.parseInt(lbString);
        }
        if(ubString != null && !ubString.isEmpty()){
            ub = Integer.parseInt(ubString);
        }
        if(priceString != null && !priceString.isEmpty()){
            price = Double.parseDouble(priceString);
        }

        try {
            roomTypeRestClient.updateRoomType(dialog.getRoomTypeDto().getId(), price, category, lb, ub);

            RoomTypesDialog.getInstance().clean();
            List<RoomTypeDto> roomTypes = roomTypeRestClient.getRoomTypesByHotel(ManagerView.getInstance().getHotelDto().getId());

            RoomTypesDialog.getInstance().getVbRoomTypes().getChildren().clear();
            for (RoomTypeDto roomTypeDto: roomTypes){
                RoomTypesDialog.getInstance().getVbRoomTypes().getChildren().add(new RoomTypePane(roomTypeDto));
            }

            dialog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
