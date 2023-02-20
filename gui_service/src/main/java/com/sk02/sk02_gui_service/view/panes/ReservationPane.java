package com.sk02.sk02_gui_service.view.panes;

import com.sk02.sk02_gui_service.controller.CancelReservationController;
import com.sk02.sk02_gui_service.restclient.dto.reservation.ReservationDto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReservationPane extends VBox {

    private ReservationDto reservationDto;

    public ReservationPane(ReservationDto reservationDto){
        this.reservationDto = reservationDto;
        init();
    }

    private void init(){
        this.getStyleClass().add("custom-pane");

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label lblHotel = new Label(reservationDto.getHotelName());
        Label lblCategory = new Label(reservationDto.getRoomTypeCategory());

        HBox hbDates = new HBox();
        hbDates.setSpacing(5);
        hbDates.setAlignment(Pos.CENTER);

        Label lblStartDate = new Label(reservationDto.getStartDate().toString());
        Label lblEndDate = new Label(reservationDto.getEndDate().toString());
        Label lblDash = new Label(" - ");

        hbDates.getChildren().addAll(lblStartDate, lblDash, lblEndDate);

        Label lblPrice = new Label(String.valueOf(reservationDto.getPrice()));

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(80);
        btnCancel.getStyleClass().add("button-orange");

        btnCancel.setOnAction(new CancelReservationController(reservationDto));

        this.getChildren().addAll(lblHotel, lblCategory, hbDates, lblPrice, btnCancel);
    }
}
