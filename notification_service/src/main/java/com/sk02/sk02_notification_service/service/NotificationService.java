package com.sk02.sk02_notification_service.service;

import com.sk02.sk02_notification_service.dto.notification.NotificationCreateDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationUpdateDto;

import java.util.List;

public interface NotificationService {

    NotificationDto createNotification(NotificationCreateDto notificationCreateDto);
    NotificationDto updateNotification(Long id, NotificationUpdateDto notificationUpdateDto);
    void deleteNotification(Long id);

    List<NotificationDto> getAllNotifications();
}
