package com.sk02.sk02_user_service.controller;

import com.sk02.sk02_user_service.security.CheckSecurity;
import com.sk02.sk02_user_service.service.ClientAttributesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-attributes")
public class ClientAttributesController {

    private final ClientAttributesService clientAttributesService;

    public ClientAttributesController(ClientAttributesService clientAttributesService) {
        this.clientAttributesService = clientAttributesService;
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<HttpStatus> updateReservationNumber (@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        clientAttributesService.updateClientReservations(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cancel/{username}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<HttpStatus> updateCancelNumber (@RequestHeader("Authorization") String authorization, @PathVariable("username") String username){
        clientAttributesService.updateClientCancellation(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
