package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.ReviewRestClient;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.MyReviewsDialog;
import com.sk02.sk02_gui_service.view.panes.MyReviewPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class DeleteReviewController implements EventHandler<ActionEvent> {

    private ReviewDto reviewDto;
    private ReviewRestClient reviewRestClient;

    public DeleteReviewController(ReviewDto reviewDto){
        this.reviewDto = reviewDto;
        reviewRestClient = new ReviewRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            reviewRestClient.deleteReview(reviewDto.getId());

            List<ReviewDto> clientReviews = reviewRestClient.getClientReviews();
            MyReviewsDialog.getInstance().getVbReviews().getChildren().clear();

            for (ReviewDto reviewDto: clientReviews){
                MyReviewsDialog.getInstance().getVbReviews().getChildren().add(new MyReviewPane(reviewDto));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
