package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.HotelRestClient;
import com.sk02.sk02_gui_service.restclient.clients.reservation.ReviewRestClient;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelDto;
import com.sk02.sk02_gui_service.view.client.ClientView;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.AddReviewDialog;
import com.sk02.sk02_gui_service.view.shared.ErrorDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class AddReviewController implements EventHandler<ActionEvent> {

    private ReviewRestClient reviewRestClient;
    private AddReviewDialog addReviewDialog;

    public AddReviewController(AddReviewDialog addReviewDialog){
        reviewRestClient = new ReviewRestClient();
        this.addReviewDialog = addReviewDialog;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String comment = addReviewDialog.getTaComment().getText();
        int rate = (int) addReviewDialog.getSliderRating().getValue();
        HotelDto hotelDto = (HotelDto) addReviewDialog.getCbHotels().getValue();

        if (comment == null || comment.isEmpty() || hotelDto == null){
            new ErrorDialog("Wrong Input!").show();
            return;
        }

        try {
            reviewRestClient.addReview(comment, rate, hotelDto.getId());

            ClientView.getInstance().refresh();
            addReviewDialog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
