package com.sk02.sk02_gui_service.view.client.dialogs.reviews;

import com.sk02.sk02_gui_service.view.LoginView;
import com.sk02.sk02_gui_service.view.client.ClientView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyReviewsDialog extends Stage {

    private static MyReviewsDialog instance;

    private VBox vbReviews;

    private MyReviewsDialog(){
        init();
    }

    public static MyReviewsDialog getInstance(){
        if(instance == null){
            instance = new MyReviewsDialog();
        }
        return instance;
    }

    private void init(){
        setTitle("My Reviews");

        //title
        Label lblTitle = new Label("My Reviews");
        lblTitle.getStyleClass().add("title");
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitle);

        //review list
        vbReviews = new VBox();
        vbReviews.setAlignment(Pos.CENTER);
        vbReviews.setPadding(new Insets(20));
        vbReviews.setSpacing(20);

        ScrollPane spReviews = new ScrollPane();
        spReviews.setContent(vbReviews);
        spReviews.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spReviews.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        vbReviews.minWidthProperty().bind(spReviews.widthProperty().multiply(0.95));

        //button
        Button btnBack = new Button("Back");
        btnBack.setMinWidth(80);
        btnBack.getStyleClass().add("button-orange");

        btnBack.setOnAction(actionEvent -> {
            ClientView.getInstance().refresh();
            this.close();
        });

        HBox hbButtons = new HBox();
        hbButtons.setPadding(new Insets(10));
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.getChildren().add(btnBack);

        //scene settings
        BorderPane bp = new BorderPane();
        bp.setTop(hbTitle);
        bp.setCenter(spReviews);
        bp.setBottom(hbButtons);

        Scene scene = new Scene(bp, 500, 600);
        setMinWidth(510);
        setMinHeight(410);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
    }

    public VBox getVbReviews() {
        return vbReviews;
    }
}
