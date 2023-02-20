package com.sk02.sk02_notification_service.controller;

import com.sk02.sk02_notification_service.dto.archived.ANFilterDto;
import com.sk02.sk02_notification_service.dto.archived.ArchivedNotificationDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationCreateDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationUpdateDto;
import com.sk02.sk02_notification_service.security.CheckSecurity;
import com.sk02.sk02_notification_service.service.ArchivedNotificationService;
import com.sk02.sk02_notification_service.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final ArchivedNotificationService archivedNotificationService;

    public NotificationController(NotificationService notificationService, ArchivedNotificationService archivedNotificationService) {
        this.notificationService = notificationService;
        this.archivedNotificationService = archivedNotificationService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<List<NotificationDto>> getNotifications(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<NotificationDto> createNotification(@RequestHeader("Authorization") String authorization, @RequestBody NotificationCreateDto notificationCreateDto) {
        return new ResponseEntity<>(notificationService.createNotification(notificationCreateDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<NotificationDto> updateNotification(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, @RequestBody NotificationUpdateDto notificationUpdateDto) {
        return new ResponseEntity<>(notificationService.updateNotification(id, notificationUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<HttpStatus> deleteNotification(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/archived")
    @CheckSecurity(roles = {"ADMIN", "MANAGER", "CLIENT"})
    public ResponseEntity<List<ArchivedNotificationDto>> getArchivedNotifications(@RequestHeader("Authorization") String authorization, @RequestBody ANFilterDto anFilterDto) {
        return new ResponseEntity<>(archivedNotificationService.getNotifications(anFilterDto, authorization), HttpStatus.OK);
    }
}
