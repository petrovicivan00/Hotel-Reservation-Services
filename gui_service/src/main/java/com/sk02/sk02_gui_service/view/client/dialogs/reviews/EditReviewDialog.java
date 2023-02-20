package com.sk02.sk02_gui_service.view.client.dialogs.reviews;

import com.sk02.sk02_gui_service.controller.EditReviewController;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditReviewDialog extends Stage {

    private TextArea taComment;
    private Slider sliderRating;

    private ReviewDto reviewDto;

    public EditReviewDialog(ReviewDto reviewDto){
        this.reviewDto = reviewDto;
        init();
    }

    private void init(){
        setTitle("Edit A Review");

        //title
        Label lblTitle = new Label("Edit Your Review");
        lblTitle.getStyleClass().add("title");
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitle);

        //comment
        taComment = new TextArea();
        taComment.setPromptText("Edit your comment here...");
        taComment.setWrapText(true);

        //rating
        Label lblRating = new Label("Rating:");
        sliderRating = new Slider(1, 5, 3);
        sliderRating.setBlockIncrement(1);
        sliderRating.setMajorTickUnit(1);
        sliderRating.setMinorTickCount(0);
        sliderRating.setShowTickLabels(true);
        sliderRating.setSnapToTicks(true);

        HBox hbRating = new HBox();
        hbRating.setPadding(new Insets(10));
        hbRating.setAlignment(Pos.CENTER);
        hbRating.setSpacing(10);
        hbRating.getChildren().addAll(lblRating, sliderRating);

        //center vb
        VBox vbMain = new VBox();
        vbMain.setAlignment(Pos.CENTER);
        vbMain.setPadding(new Insets(15));
        vbMain.getChildren().addAll(taComment, hbRating);

        //buttons
        Button btnEdit = new Button("Edit");
        btnEdit.setMinWidth(80);
        btnEdit.getStyleClass().add("button-blue");

        btnEdit.setOnAction(new EditReviewController(this));

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(80);
        btnCancel.getStyleClass().add("button-orange");

        btnCancel.setOnAction(actionEvent -> {
            this.close();
        });

        HBox hbButtons = new HBox();
        hbButtons.setPadding(new Insets(10));
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnEdit, btnCancel);

        //scene settings
        BorderPane bp = new BorderPane();
        bp.setTop(hbTitle);
        bp.setCenter(vbMain);
        bp.setBottom(hbButtons);

        Scene scene = new Scene(bp, 500, 400);
        setMinWidth(510);
        setMinHeight(410);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
    }

    public TextArea getTaComment() {
        return taComment;
    }

    public Slider getSliderRating() {
        return sliderRating;
    }

    public ReviewDto getReviewDto() {
        return reviewDto;
    }
}
