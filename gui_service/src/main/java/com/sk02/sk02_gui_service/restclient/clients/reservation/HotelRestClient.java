package com.sk02.sk02_gui_service.restclient.clients.reservation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelDto;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelFilterDto;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelFilterViewDto;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelUpdateDto;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HotelRestClient {

    //public static final String URL = "http://localhost:8082/api";
    public static final String URL = "http://localhost:8084/reservation-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public List<HotelFilterViewDto> filterHotels(String name, String city, LocalDate starDate, LocalDate endDate, String priceSort) throws IOException {
        HotelFilterDto hotelFilterDto = new HotelFilterDto();

        if (name != null && !name.isEmpty()){
            hotelFilterDto.setName(name);
        }
        if(city != null && !city.isEmpty()){
            hotelFilterDto.setCity(city);
        }
        if(starDate != null){
            hotelFilterDto.setStartDate(starDate);
        }
        if(endDate != null){
            hotelFilterDto.setEndDate(endDate);
        }

        if(priceSort != null && priceSort.equals("Low To High")){
            hotelFilterDto.setPriceSort("ASC");
        }
        else if(priceSort != null && priceSort.equals("High To Low")){
            hotelFilterDto.setPriceSort("DESC");
        }
        else {
            hotelFilterDto.setPriceSort(null);
        }

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(hotelFilterDto), JSON);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/hotels/filter")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .post(body)
                .build();


        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if (response.code() == 200) {
            String json = response.body().string();

            HotelFilterViewDto[] filters = objectMapper.readValue(json, HotelFilterViewDto[].class);
            return Arrays.asList(filters);
        }

        throw new RuntimeException("Could Not Filter Hotels");
    }

    public List<HotelDto> getAllHotels() throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/hotels")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .get()
                .build();


        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if (response.code() == 200) {
            String json = response.body().string();

            HotelDto[] hotels = objectMapper.readValue(json, HotelDto[].class);
            return Arrays.asList(hotels);
        }

        throw new RuntimeException("Could Not Get Hotels");
    }

    public HotelDto getManagerHotel() throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/hotels/manager-hotel")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            String json = response.body().string();

            return objectMapper.readValue(json, HotelDto.class);
        }

        throw new RuntimeException("Getting Hotel Failed");
    }

    public void updateHotel(Long hotelId, String name, String description, String city) throws IOException{
        HotelUpdateDto hotelUpdateDto = new HotelUpdateDto();

        if(name != null && !name.isEmpty()){
            hotelUpdateDto.setName(name);
        }
        if(description != null && !description.isEmpty()){
            hotelUpdateDto.setDescription(description);
        }
        if(city != null && !city.isEmpty()){
            hotelUpdateDto.setCity(city);
        }

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(hotelUpdateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/hotels/" + hotelId)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .put(body)
                .build();


        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if (response.code() == 200) {
           return;
        }

        throw new RuntimeException("Could Not Update Hotel");
    }
}
