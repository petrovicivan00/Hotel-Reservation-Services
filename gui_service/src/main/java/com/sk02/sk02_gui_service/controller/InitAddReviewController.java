package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.HotelRestClient;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelDto;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.AddReviewDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class InitAddReviewController implements EventHandler<ActionEvent> {

    private HotelRestClient hotelRestClient;

    public InitAddReviewController(){
        hotelRestClient = new HotelRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        AddReviewDialog addReviewDialog = new AddReviewDialog();

        try {
            List<HotelDto> hotels = hotelRestClient.getAllHotels();

            ObservableList<HotelDto> hotelsOL = FXCollections.observableArrayList(hotels);
            addReviewDialog.getCbHotels().getItems().addAll(hotelsOL);

            addReviewDialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
