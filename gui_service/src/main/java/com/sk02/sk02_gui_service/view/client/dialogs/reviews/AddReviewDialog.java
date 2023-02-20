package com.sk02.sk02_gui_service.view.client.dialogs.reviews;

import com.sk02.sk02_gui_service.controller.AddReviewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddReviewDialog extends Stage {

    private ComboBox cbHotels;
    private TextArea taComment;
    private Slider sliderRating;

    public AddReviewDialog(){
        init();
    }

    private void init(){
        setTitle("Write A Review");

        //title
        Label lblTitle = new Label("Write A Review");
        lblTitle.getStyleClass().add("title");
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitle);

        //hotel picker
        Label lblHotel = new Label("Hotel:");
        cbHotels = new ComboBox();
        cbHotels.setPromptText("Select Hotel");

        HBox hbHotelPicker = new HBox();
        hbHotelPicker.setPadding(new Insets(10));
        hbHotelPicker.setAlignment(Pos.CENTER);
        hbHotelPicker.setSpacing(10);
        hbHotelPicker.getChildren().addAll(lblHotel, cbHotels);

        //comment
        taComment = new TextArea();
        taComment.setPromptText("Enter your comment here...");
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
        vbMain.getChildren().addAll(hbHotelPicker, taComment, hbRating);

        //buttons
        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(80);
        btnAdd.getStyleClass().add("button-blue");

        btnAdd.setOnAction(new AddReviewController(this));

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
        hbButtons.getChildren().addAll(btnAdd, btnCancel);

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
        initModality(Modality.APPLICATION_MODAL);
    }

    public ComboBox getCbHotels() {
        return cbHotels;
    }

    public TextArea getTaComment() {
        return taComment;
    }

    public Slider getSliderRating() {
        return sliderRating;
    }
}
