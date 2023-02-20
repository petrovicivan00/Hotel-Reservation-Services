package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.ReviewRestClient;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.MyReviewsDialog;
import com.sk02.sk02_gui_service.view.panes.MyReviewPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class MyReviewsController implements EventHandler<ActionEvent> {

    private ReviewRestClient reviewRestClient;

    public MyReviewsController() {
        reviewRestClient = new ReviewRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        try {
            List<ReviewDto> clientReviews = reviewRestClient.getClientReviews();
            MyReviewsDialog.getInstance().getVbReviews().getChildren().clear();

            for (ReviewDto reviewDto: clientReviews){
                MyReviewsDialog.getInstance().getVbReviews().getChildren().add(new MyReviewPane(reviewDto));
            }
            MyReviewsDialog.getInstance().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
