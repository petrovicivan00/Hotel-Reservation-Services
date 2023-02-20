package com.sk02.sk02_user_service.controller;

import com.sk02.sk02_user_service.dto.attributes.ManagerAttributesDto;
import com.sk02.sk02_user_service.dto.token.TokenRequestDto;
import com.sk02.sk02_user_service.dto.token.TokenResponseDto;
import com.sk02.sk02_user_service.dto.user.*;
import com.sk02.sk02_user_service.security.CheckSecurity;
import com.sk02.sk02_user_service.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping
    @CheckSecurity (roles = {"ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(userService.findAll(PageRequest.of(0, 20)), HttpStatus.OK);
    }

    @GetMapping ("/{username}")
    @CheckSecurity (roles = {"ADMIN"})
    public ResponseEntity<UserDto> getUserByUsername(@RequestHeader("Authorization") String authorization, @PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping ("/client")
    public ResponseEntity<UserDto> createUserClient(@RequestBody @Valid UserClientCreateDto userClientCreateDto) {
        return new ResponseEntity<>(userService.createClient(userClientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping ("/client")
    @CheckSecurity (roles = {"CLIENT"})
    public ResponseEntity<UserDto> updateUserClient(@RequestHeader("Authorization") String authorization, @RequestBody UserClientUpdateDto userClientUpdateDto) {
        return new ResponseEntity<>(userService.updateUserClient(authorization, userClientUpdateDto), HttpStatus.OK);
    }

    @PostMapping ("/manager")
    public ResponseEntity<UserDto> createUserManager(@RequestBody @Valid UserManagerCreateDto userManagerCreateDto) {
        return new ResponseEntity<>(userService.createManager(userManagerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping ("/manager")
    @CheckSecurity (roles = {"MANAGER"})
    public ResponseEntity<UserDto> updateUserManager(@RequestHeader("Authorization") String authorization, @RequestBody UserManagerUpdateDto userManagerUpdateDto) {
        return new ResponseEntity<>(userService.updateUserManager(authorization, userManagerUpdateDto), HttpStatus.OK);
    }

    @PutMapping ("/delete/{id}")
    @CheckSecurity (roles = {"ADMIN"})
    public ResponseEntity<HttpStatus> deleteUserByAdmin(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping ("/activate-admin/{id}")
    @CheckSecurity (roles = {"ADMIN"})
    public ResponseEntity<HttpStatus> activateUserByAdmin(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        userService.activateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateUser(@PathVariable("id") Long id) {
        userService.activateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/manager/{hotelName}")
    @CheckSecurity (roles = {"ADMIN"})
    public ResponseEntity<UserDto> findManagerByHotelName(@RequestHeader("Authorization") String authorization, @PathVariable("hotelName") String hotelName) {
        return new ResponseEntity<>(userService.findManagerByHotelName(hotelName), HttpStatus.OK);
    }
}
