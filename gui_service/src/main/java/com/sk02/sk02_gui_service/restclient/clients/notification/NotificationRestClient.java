package com.sk02.sk02_gui_service.restclient.clients.notification;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelFilterViewDto;
import com.sk02.sk02_gui_service.restclient.dto.notification.ANFilterDto;
import com.sk02.sk02_gui_service.restclient.dto.notification.ArchivedNotificationDto;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class NotificationRestClient {

    public static final String URL = "http://localhost:8084/notification-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public List<ArchivedNotificationDto> getArchivedNotifications(String type, LocalDate dateStart, LocalDate dateEnd) throws IOException {
        ANFilterDto anFilterDto = new ANFilterDto();

        if(type != null && !type.isEmpty()){
            anFilterDto.setType(type);
        }
        if(dateStart != null && dateEnd != null){
            anFilterDto.setStartDate(dateStart);
            anFilterDto.setEndDate(dateEnd);
        }

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(anFilterDto), JSON);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/notifications/archived")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .post(body)
                .build();


        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if (response.code() == 200) {
            String json = response.body().string();

            ArchivedNotificationDto[] notifications = objectMapper.readValue(json, ArchivedNotificationDto[].class);
            return Arrays.asList(notifications);
        }

        throw new RuntimeException("Could Not Filter Notifications");
    }
}
