package com.sk02.sk02_gui_service.restclient.clients.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.dto.notification.ArchivedNotificationDto;
import com.sk02.sk02_gui_service.restclient.dto.reservation.ReservationCreateDto;
import com.sk02.sk02_gui_service.restclient.dto.reservation.ReservationDto;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReservationRestClient {

    public static final String URL = "http://localhost:8084/reservation-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public void makeReservation(Date startDate, Date endDate, Long hotelId, Long roomTypeId) throws IOException {
        ReservationCreateDto reservationCreateDto = new ReservationCreateDto();
        reservationCreateDto.setStartDate(startDate);
        reservationCreateDto.setEndDate(endDate);
        reservationCreateDto.setHotelId(hotelId);
        reservationCreateDto.setRoomTypeId(roomTypeId);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(reservationCreateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/reservations")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 201){
            return;
        }

        throw new RuntimeException("Reservation Failed");
    }

    public List<ReservationDto> getClientReservations() throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/reservations")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            String json = response.body().string();

            ReservationDto[] reservations = objectMapper.readValue(json, ReservationDto[].class);
            return Arrays.asList(reservations);
        }

        throw new RuntimeException("Getting Reservations Failed");
    }

    public List<ReservationDto> getManagerReservations() throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/reservations/manager")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            String json = response.body().string();

            ReservationDto[] reservations = objectMapper.readValue(json, ReservationDto[].class);
            return Arrays.asList(reservations);
        }

        throw new RuntimeException("Getting Reservations Failed");
    }

    public void cancelReservation(Long id) throws IOException{
        String route = "";

        if (UserData.getInstance().getRole().equals("CLIENT")){
            route = "/reservations/";
        }
        else if (UserData.getInstance().getRole().equals("MANAGER")){
            route = "/reservations/manager/";
        }

        Request request = new Request.Builder()
                .url(URL + route + id)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .delete()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            return;
        }

        throw new RuntimeException("Reservation Cancellation Failed");
    }
}
