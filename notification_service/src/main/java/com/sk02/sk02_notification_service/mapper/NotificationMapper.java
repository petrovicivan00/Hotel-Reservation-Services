package com.sk02.sk02_notification_service.mapper;

import com.sk02.sk02_notification_service.domain.Notification;
import com.sk02.sk02_notification_service.dto.notification.NotificationCreateDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification notificationCreateDtoToNotification(NotificationCreateDto notificationCreateDto) {
        Notification notification = new Notification();

        notification.setType(notificationCreateDto.getType());
        notification.setSubject(notificationCreateDto.getSubject());
        notification.setMessage(notificationCreateDto.getMessage());

        return notification;
    }

    public NotificationDto notificationToNotificationDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setId(notification.getId());
        notificationDto.setType(notification.getType());
        notificationDto.setSubject(notification.getSubject());
        notificationDto.setMessage(notification.getMessage());

        return notificationDto;
    }

    public void updateNotification(Notification notification, NotificationUpdateDto notificationUpdateDto) {
        if (notificationUpdateDto.getType() != null)
            notification.setType(notificationUpdateDto.getType());

        if (notificationUpdateDto.getSubject() != null)
            notification.setSubject(notificationUpdateDto.getSubject());

        if (notificationUpdateDto.getMessage() != null)
            notification.setMessage(notificationUpdateDto.getMessage());
    }
}
