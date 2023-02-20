package com.sk02.sk02_gui_service.view.panes;

import com.sk02.sk02_gui_service.controller.DeleteReviewController;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import com.sk02.sk02_gui_service.view.client.dialogs.reviews.EditReviewDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MyReviewPane extends VBox {

    private ReviewDto reviewDto;

    public MyReviewPane(ReviewDto reviewDto){
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

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);

        Button btnEdit = new Button("Edit");
        btnEdit.setMinWidth(100);
        btnEdit.getStyleClass().add("button-blue");

        btnEdit.setOnAction(actionEvent -> {
            new EditReviewDialog(reviewDto).show();
        });

        Button btnDelete = new Button("Delete");
        btnDelete.setMinWidth(100);
        btnDelete.getStyleClass().add("button-orange");

        btnDelete.setOnAction(new DeleteReviewController(reviewDto));

        hbButtons.getChildren().addAll(btnEdit, btnDelete);

        this.getChildren().addAll(lblUsername, taComment, hbBottom, hbButtons);
    }

    public ReviewDto getReviewDto() {
        return reviewDto;
    }
}
