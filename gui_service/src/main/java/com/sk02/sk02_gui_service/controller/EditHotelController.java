package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.HotelRestClient;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import com.sk02.sk02_gui_service.view.manager.dialogs.EditHotelDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class EditHotelController implements EventHandler<ActionEvent> {

    private HotelRestClient hotelRestClient;
    private EditHotelDialog dialog;

    public EditHotelController(EditHotelDialog dialog){
        this.dialog = dialog;
        hotelRestClient = new HotelRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String name = dialog.getTfName().getText();
        String description = dialog.getTfDesc().getText();
        String city = dialog.getTfCity().getText();

        try {
            hotelRestClient.updateHotel(ManagerView.getInstance().getHotelDto().getId(), name, description, city);

            ManagerView.getInstance().refresh();
            dialog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
