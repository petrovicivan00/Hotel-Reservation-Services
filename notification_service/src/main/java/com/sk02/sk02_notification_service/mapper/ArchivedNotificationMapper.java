package com.sk02.sk02_notification_service.mapper;

import com.sk02.sk02_notification_service.domain.ArchivedNotification;
import com.sk02.sk02_notification_service.dto.archived.ArchivedNotificationDto;
import org.springframework.stereotype.Component;

@Component
public class ArchivedNotificationMapper {

    public ArchivedNotificationDto anToAnDto(ArchivedNotification archivedNotification) {
        ArchivedNotificationDto archivedNotificationDto = new ArchivedNotificationDto();

        archivedNotificationDto.setId(archivedNotification.getId());
        archivedNotificationDto.setType(archivedNotification.getType());
        archivedNotificationDto.setEmail(archivedNotification.getEmail());
        archivedNotificationDto.setSubject(archivedNotification.getSubject());
        archivedNotificationDto.setMessage(archivedNotification.getMessage());
        archivedNotificationDto.setCreated(archivedNotification.getCreated());

        return archivedNotificationDto;
    }
}
