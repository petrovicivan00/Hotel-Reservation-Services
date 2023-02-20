package com.sk02.sk02_gui_service.restclient.clients.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeCreateDto;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeDto;
import com.sk02.sk02_gui_service.restclient.dto.roomtype.RoomTypeUpdateDto;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RoomTypeRestClient {

    public static final String URL = "http://localhost:8084/reservation-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public List<RoomTypeDto> getRoomTypesByHotel(Long hotelId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/room-types/" + hotelId)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if (response.code() == 200) {
            String json = response.body().string();

            RoomTypeDto[] roomTypes = objectMapper.readValue(json, RoomTypeDto[].class);
            return Arrays.asList(roomTypes);
        }

        throw new RuntimeException();
    }

    public void deleteRoomType(Long roomTypeId) throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/room-types/" + roomTypeId)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .delete()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            return;
        }

        throw new RuntimeException("Deleting Room Type Failed");
    }

    public void addRoomType(Long hotelId, double price, String category, int lowerBound, int upperBound) throws IOException{
        RoomTypeCreateDto roomTypeCreateDto = new RoomTypeCreateDto();
        roomTypeCreateDto.setCategory(category);
        roomTypeCreateDto.setPrice(price);
        roomTypeCreateDto.setLowerBound(lowerBound);
        roomTypeCreateDto.setUpperBound(upperBound);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(roomTypeCreateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/room-types/" + hotelId)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 201){
            return;
        }

        throw new RuntimeException("Creating Room Type Failed");
    }

    public void updateRoomType(Long hotelId, double price, String category, int lowerBound, int upperBound) throws IOException{
        RoomTypeUpdateDto roomTypeUpdateDto = new RoomTypeUpdateDto();

        if(category != null && !category.isEmpty()){
            roomTypeUpdateDto.setCategory(category);
        }
        roomTypeUpdateDto.setPrice(price);
        roomTypeUpdateDto.setLowerBound(lowerBound);
        roomTypeUpdateDto.setUpperBound(upperBound);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(roomTypeUpdateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/room-types/" + hotelId)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .put(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            return;
        }

        throw new RuntimeException("Editing Room Type Failed");
    }
}
