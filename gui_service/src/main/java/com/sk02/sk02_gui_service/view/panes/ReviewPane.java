package com.sk02.sk02_gui_service.view.panes;

import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReviewPane extends VBox {

    private ReviewDto reviewDto;

    public ReviewPane(ReviewDto reviewDto){
        this.reviewDto = reviewDto;
        init();
    }

    private void init(){
        this.getStyleClass().add("custom-pane");

        this.setPadding(new Insets(30));
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_LEFT);

        Label lblUsername = new Label(reviewDto.getUsername());
        lblUsername.getStyleClass().add("title-pane");

        TextArea taComment = new TextArea(reviewDto.getComment());
        taComment.maxWidthProperty().bind(this.widthProperty().multiply(0.8));
        taComment.setEditable(false);
        taComment.setWrapText(true);
        taComment.setMaxHeight(100);

        HBox hbBottom = new HBox();
        hbBottom.setSpacing(10);
        hbBottom.getChildren().addAll(new Label("Hotel:"), new Label(reviewDto.getHotelName()), new Label("Rating:"), new Label(String.valueOf(reviewDto.getRate())));

        this.getChildren().addAll(lblUsername, taComment, hbBottom);
    }

    public ReviewDto getReviewDto() {
        return reviewDto;
    }
}
