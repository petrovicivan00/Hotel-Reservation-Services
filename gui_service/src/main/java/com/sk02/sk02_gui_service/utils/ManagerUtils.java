package com.sk02.sk02_gui_service.utils;

import com.sk02.sk02_gui_service.restclient.clients.reservation.HotelRestClient;
import com.sk02.sk02_gui_service.restclient.clients.user.UserRestClient;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelDto;
import com.sk02.sk02_gui_service.view.manager.ManagerView;

import java.io.IOException;

public class ManagerUtils {

    private HotelRestClient hotelRestClient;

    public ManagerUtils(){
        hotelRestClient = new HotelRestClient();
    }

    public void refresh(){
        try {
            HotelDto hotel = hotelRestClient.getManagerHotel();

            ManagerView.getInstance().setHotelDto(hotel);

            ManagerView.getInstance().clean();
            ManagerView.getInstance().getLblName().setText(hotel.getName());
            ManagerView.getInstance().getLblCity().setText(hotel.getCity());
            ManagerView.getInstance().getLblDescription().setText(hotel.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
