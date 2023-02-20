package com.sk02.sk02_user_service.controller;

import com.sk02.sk02_user_service.dto.attributes.HotelNewNameDto;
import com.sk02.sk02_user_service.dto.attributes.ManagerAttributesDto;
import com.sk02.sk02_user_service.security.CheckSecurity;
import com.sk02.sk02_user_service.service.ManagerAttributeService;
import com.sk02.sk02_user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager-attributes")
public class ManagerAttributesController {

    private final UserService userService;
    private final ManagerAttributeService managerAttributeService;

    public ManagerAttributesController(UserService userService, ManagerAttributeService managerAttributeService) {
        this.userService = userService;
        this.managerAttributeService = managerAttributeService;
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ADMIN", "MANAGER"})
    public ResponseEntity<ManagerAttributesDto> getManagerAttributesById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getManagerAttributesByManagerId(id), HttpStatus.OK);
    }

    @PutMapping("/new-name")
    @CheckSecurity(roles = {"ADMIN", "MANAGER"})
    public ResponseEntity<ManagerAttributesDto> updateNewHotelName(@RequestHeader("Authorization") String authorization, @RequestBody HotelNewNameDto hotelNewNameDto){
        return new ResponseEntity<>(managerAttributeService.updateNewName(hotelNewNameDto), HttpStatus.OK);
    }
}
