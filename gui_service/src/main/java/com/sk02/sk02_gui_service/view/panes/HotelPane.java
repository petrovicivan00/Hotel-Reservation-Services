package com.sk02.sk02_gui_service.view.panes;

import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelFilterViewDto;
import com.sk02.sk02_gui_service.view.client.dialogs.ReservationDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HotelPane extends BorderPane {

    private HotelFilterViewDto hotelFilterViewDto;

    public HotelPane(HotelFilterViewDto hotelFilterViewDto){
        this.hotelFilterViewDto = hotelFilterViewDto;
        init();
    }

    private void init(){
        this.getStyleClass().add("custom-pane-clickable");

        VBox vbTop = new VBox();
        vbTop.setPadding(new Insets(10));
        vbTop.setSpacing(10);
        vbTop.setAlignment(Pos.CENTER);

        Label lblTitle = new Label(hotelFilterViewDto.getHotelName());
        lblTitle.getStyleClass().add("title-pane");

        vbTop.getChildren().addAll(lblTitle, new Label(hotelFilterViewDto.getHotelDescription()));

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(100);
        gridPane.setVgap(10);
        gridPane.setMinWidth(500);
        gridPane.setPadding(new Insets(40));

        gridPane.add(new Label("City"), 0, 0);
        gridPane.add(new Label(hotelFilterViewDto.getHotelCity()), 0, 1);

        gridPane.add(new Label("Category"), 1, 0);
        gridPane.add(new Label(hotelFilterViewDto.getRoomTypeCategory()), 1, 1);

        gridPane.add(new Label("Price"), 2, 0);
        gridPane.add(new Label(hotelFilterViewDto.getRoomTypePrice()), 2, 1);

        this.setTop(vbTop);
        this.setCenter(gridPane);

        this.setOnMouseClicked(mouseEvent -> {
            new ReservationDialog(hotelFilterViewDto).show();
        });
    }

    public HotelFilterViewDto getHotelFilterViewDto() {
        return hotelFilterViewDto;
    }
}
