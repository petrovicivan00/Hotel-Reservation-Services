package com.sk02.sk02_gui_service.restclient.clients.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk02.sk02_gui_service.model.UserData;
import com.sk02.sk02_gui_service.restclient.dto.hotel.HotelDto;
import com.sk02.sk02_gui_service.restclient.dto.token.TokenRequestDto;
import com.sk02.sk02_gui_service.restclient.dto.token.TokenResponseDto;
import com.sk02.sk02_gui_service.restclient.dto.user.UserClientCreateDto;
import com.sk02.sk02_gui_service.restclient.dto.user.UserClientUpdateDto;
import com.sk02.sk02_gui_service.restclient.dto.user.UserManagerCreateDto;
import com.sk02.sk02_gui_service.restclient.dto.user.UserManagerUpdateDto;
import okhttp3.*;

import java.io.IOException;
import java.util.Date;

public class UserRestClient {

    public static final String URL = "http://localhost:8084/user-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    //public static final String URL = "http://localhost:8081/api";

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String login(String email, String password) throws IOException {

        TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);
        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(tokenRequestDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/users/login")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if (response.code() == 200) {
            String json = response.body().string();
            TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

            return dto.getToken();
        }

        throw new RuntimeException("Invalid username or password");
    }

    public void registerClient(String firstName, String lastName, String username, String password, String email, String phone, Date birthday, String passportNo) throws IOException{

        UserClientCreateDto userClientCreateDto = new UserClientCreateDto();
        userClientCreateDto.setFirstName(firstName);
        userClientCreateDto.setLastName(lastName);
        userClientCreateDto.setUsername(username);
        userClientCreateDto.setPassword(password);
        userClientCreateDto.setEmail(email);
        userClientCreateDto.setPhone(phone);
        userClientCreateDto.setBirthday(birthday);
        userClientCreateDto.setPassportNumber(passportNo);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(userClientCreateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/users/client")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 201){
            return;
        }

        throw new RuntimeException("Registration Failed");
    }

    public void registerManager(String firstName, String lastName, String username, String password, String email, String phone, Date birthday, String hotelName) throws IOException{

        UserManagerCreateDto userManagerCreateDto = new UserManagerCreateDto();
        userManagerCreateDto.setFirstName(firstName);
        userManagerCreateDto.setLastName(lastName);
        userManagerCreateDto.setUsername(username);
        userManagerCreateDto.setPassword(password);
        userManagerCreateDto.setEmail(email);
        userManagerCreateDto.setPhone(phone);
        userManagerCreateDto.setBirthday(birthday);
        userManagerCreateDto.setHotelName(hotelName);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(userManagerCreateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/users/manager")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if(response.code() == 201){
            return;
        }

        throw new RuntimeException("Registration Failed");
    }

    public void editClient(String firstName, String lastName, String username, String password, String email, String phone, Date birthday, String passportNo) throws IOException{
        UserClientUpdateDto userClientUpdateDto = new UserClientUpdateDto();

        if(firstName != null && !firstName.isEmpty()){
            userClientUpdateDto.setFirstName(firstName);
        }
        if(lastName != null && !lastName.isEmpty()){
            userClientUpdateDto.setLastName(lastName);
        }
        if(username != null && !username.isEmpty()){
            userClientUpdateDto.setUsername(username);
        }
        if(password != null && !password.isEmpty()){
            userClientUpdateDto.setPassword(password);
        }
        if(email != null && !email.isEmpty()){
            userClientUpdateDto.setEmail(email);
        }
        if(phone != null && !phone.isEmpty()){
            userClientUpdateDto.setPhone(phone);
        }
        if(birthday != null){
            userClientUpdateDto.setBirthday(birthday);
        }
        if(passportNo != null && !passportNo.isEmpty()){
            userClientUpdateDto.setPassportNumber(passportNo);
        }

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(userClientUpdateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/users/client")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .put(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            return;
        }

        throw new RuntimeException("Profile Update Failed");
    }

    public void editManager(String firstName, String lastName, String username, String password, String email, String phone, Date birthday, String hotelName) throws IOException{
        UserManagerUpdateDto userManagerUpdateDto = new UserManagerUpdateDto();

        if(firstName != null && !firstName.isEmpty()){
            userManagerUpdateDto.setFirstName(firstName);
        }
        if(lastName != null && !lastName.isEmpty()){
            userManagerUpdateDto.setLastName(lastName);
        }
        if(username != null && !username.isEmpty()){
            userManagerUpdateDto.setUsername(username);
        }
        if(password != null && !password.isEmpty()){
            userManagerUpdateDto.setPassword(password);
        }
        if(email != null && !email.isEmpty()){
            userManagerUpdateDto.setEmail(email);
        }
        if(phone != null && !phone.isEmpty()){
            userManagerUpdateDto.setPhone(phone);
        }
        if(birthday != null){
            userManagerUpdateDto.setBirthday(birthday);
        }
        if(hotelName != null && !hotelName.isEmpty()){
            userManagerUpdateDto.setHotelName(hotelName);
        }

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(userManagerUpdateDto), JSON);

        Request request = new Request.Builder()
                .url(URL + "/users/manager")
                .header("Authorization", "Bearer " + UserData.getInstance().getToken())
                .put(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response);
        if(response.code() == 200){
            return;
        }

        throw new RuntimeException("Profile Update Failed");
    }
}
