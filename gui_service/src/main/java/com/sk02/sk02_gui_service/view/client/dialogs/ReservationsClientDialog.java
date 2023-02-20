package com.sk02.sk02_gui_service.view.client.dialogs;

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

public class ReservationsClientDialog extends Stage {

    private static ReservationsClientDialog instance;

    private VBox vbReservations;

    private ReservationsClientDialog(){
        init();
    }

    public static ReservationsClientDialog getInstance(){
        if(instance == null){
            instance = new ReservationsClientDialog();
        }
        return instance;
    }

    private void init(){
        setTitle("Reservations");

        //title
        Label lblTitle = new Label("My Reservations");
        lblTitle.getStyleClass().add("title");
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitle);

        //reservations list
        vbReservations = new VBox();
        vbReservations.setAlignment(Pos.CENTER);
        vbReservations.setPadding(new Insets(20));
        vbReservations.setSpacing(20);

        ScrollPane spReservations = new ScrollPane();
        spReservations.setContent(vbReservations);
        spReservations.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spReservations.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        vbReservations.minWidthProperty().bind(spReservations.widthProperty().multiply(0.95));

        //button
        Button btnBack = new Button("Back");
        btnBack.setMinWidth(80);
        btnBack.getStyleClass().add("button-blue");

        btnBack.setOnAction(actionEvent -> {
            this.close();
        });

        HBox hbButtons = new HBox();
        hbButtons.setPadding(new Insets(10));
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.getChildren().add(btnBack);

        //scene settings
        BorderPane bp = new BorderPane();
        bp.setTop(hbTitle);
        bp.setCenter(spReservations);
        bp.setBottom(hbButtons);

        Scene scene = new Scene(bp, 500, 450);
        setMinWidth(510);
        setMinHeight(410);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
    }

    public VBox getVbReservations() {
        return vbReservations;
    }
}
