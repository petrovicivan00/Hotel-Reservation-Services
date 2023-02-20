package com.sk02.sk02_gui_service.restclient.clients.reservation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.dto.hotel.BestHotelDto;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewCreateDto;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewDto;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewFilterDto;
import com.sk02.sk02_gui_service.restclient.dto.review.ReviewUpdateDto;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReviewRestClient {

    public static final String URL = "http://localhost:8084/reservation-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();


    public List<ReviewDto> filterReviews(String city, String hotelName) throws IOException {
        ReviewFilterDto reviewFilterDto = new ReviewFilterDto();

        if(city != null && !city.isEmpty()){
            reviewFilterDto.setCity(city);
        }
        if(hotelName != null && !hotelName.isEmpty()){
            reviewFilterDto.setHotelName(hotelName);
        }

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(reviewFilterDto), JSON);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/reviews/filter")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if (response.code() == 200) {
            String json = response.body().string();

            ReviewDto[] reviews = objectMapper.readValue(json, ReviewDto[].class);
            return Arrays.asList(reviews);
        }

        throw new RuntimeException("Could Not FIlter Reviews");
    }

    public void addReview(String comment, int rate, Long hotelId) throws IOException{
        ReviewCreateDto reviewCreateDto = new ReviewCreateDto();
        reviewCreateDto.setComment(comment);
        reviewCreateDto.setHotelId(hotelId);
        reviewCreateDto.setRate(rate);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(reviewCreateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/reviews")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 201){
            return;
        }

        throw new RuntimeException("Adding A Review Failed");
    }

    public List<BestHotelDto> getBestHotels() throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/reviews/best-hotels")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            String json = response.body().string();

            BestHotelDto[] bestHotels = objectMapper.readValue(json, BestHotelDto[].class);
            return Arrays.asList(bestHotels);
        }

        throw new RuntimeException("Finding Best Hotels Failed");
    }

    public List<ReviewDto> getClientReviews() throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/reviews")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            String json = response.body().string();

            ReviewDto[] clientReviews = objectMapper.readValue(json, ReviewDto[].class);
            return Arrays.asList(clientReviews);
        }

        throw new RuntimeException("Finding Client Reviews Failed");
    }

    public void editReview(String comment, int rate, Long id) throws IOException{
        ReviewUpdateDto reviewUpdateDto = new ReviewUpdateDto();
        reviewUpdateDto.setComment(comment);
        reviewUpdateDto.setRate(rate);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(reviewUpdateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/reviews/" + id)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .put(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            return;
        }

        throw new RuntimeException("Editing A Review Failed");
    }

    public void deleteReview(Long reviewId) throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/reviews/" + reviewId)
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .delete()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
           return;
        }

        throw new RuntimeException("Deleting Review Failed");
    }
}
