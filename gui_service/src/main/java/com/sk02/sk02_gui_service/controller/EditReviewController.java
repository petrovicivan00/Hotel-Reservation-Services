package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.ReservationRestClient;
import com.sk02.sk02_gui_service.restclient.clients.reservation.ReviewRestClient;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.EditReviewDialog;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.MyReviewsDialog;
import com.sk02.sk02_gui_service.view.panes.MyReviewPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class EditReviewController implements EventHandler<ActionEvent> {

    private EditReviewDialog editReviewDialog;
    private ReviewRestClient reviewRestClient;

    public EditReviewController(EditReviewDialog editReviewDialog) {
        this.editReviewDialog = editReviewDialog;
        reviewRestClient = new ReviewRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String comment = editReviewDialog.getTaComment().getText();
        int rating = (int) editReviewDialog.getSliderRating().getValue();

        try {
            reviewRestClient.editReview(comment, rating, editReviewDialog.getReviewDto().getId());

            List<ReviewDto> clientReviews = reviewRestClient.getClientReviews();
            MyReviewsDialog.getInstance().getVbReviews().getChildren().clear();

            for (ReviewDto reviewDto: clientReviews){
                MyReviewsDialog.getInstance().getVbReviews().getChildren().add(new MyReviewPane(reviewDto));
            }

            editReviewDialog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
