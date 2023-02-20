package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.reservation.HotelRestClient;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelFilterViewDto;
import com.sk02.sk02_gui_service.view.client.ClientView;
import com.sk02.sk02_gui_service.view.panes.HotelPane;
import com.sk02.sk02_gui_service.view.shared.ErrorDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class HotelFilterController implements EventHandler<ActionEvent> {

    private HotelRestClient hotelRestClient;

    public HotelFilterController(){
        hotelRestClient = new HotelRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        String name = ClientView.getInstance().getTfHotel().getText();
        String city = ClientView.getInstance().getTfCity().getText();

        LocalDate localDateStart = ClientView.getInstance().getDpStart().getValue();
        LocalDate localDateEnd = ClientView.getInstance().getDpEnd().getValue();
        if(localDateStart == null || localDateEnd == null){
            new ErrorDialog("You must define time period!").show();
            return;
        }

        Instant instant = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
        Date dateStart = Date.from(instant);

        instant = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
        Date dateEnd = Date.from(instant);

        String priceSort = (String) ClientView.getInstance().getCbPrice().getValue();

        try {
            List<HotelFilterViewDto> hotelFilterList = hotelRestClient.filterHotels(name, city, localDateStart, localDateEnd, priceSort);

            ClientView.getInstance().getVbHotels().getChildren().clear();
            for (HotelFilterViewDto hfv : hotelFilterList){
                ClientView.getInstance().getVbHotels().getChildren().add(new HotelPane(hfv));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
