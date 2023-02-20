package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.ReservationRestClient;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelFilterViewDto;
import com.sk02.sk02_gui_service.view.client.ClientView;
import com.sk02.sk02_gui_service.view.client.dialogs.ReservationDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.Date;

public class MakeReservationController implements EventHandler<ActionEvent> {

    private ReservationDialog reservationDialog;
    private ReservationRestClient reservationRestClient;

    public MakeReservationController(ReservationDialog reservationDialog){
        this.reservationDialog = reservationDialog;
        reservationRestClient = new ReservationRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Date startDate = reservationDialog.getHotelFilterViewDto().getStartDate();
        Date endDate = reservationDialog.getHotelFilterViewDto().getEndDate();
        Long hotelId = Long.valueOf(reservationDialog.getHotelFilterViewDto().getHotelId());
        Long roomTypeId = Long.valueOf(reservationDialog.getHotelFilterViewDto().getRoomTypeId());

        try {
            reservationRestClient.makeReservation(startDate, endDate, hotelId, roomTypeId);

            reservationDialog.close();
            ClientView.getInstance().refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
