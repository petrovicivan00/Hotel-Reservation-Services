package com.sk02.sk02_notification_service.service;

import com.sk02.sk02_notification_service.domain.ArchivedNotification;
import com.sk02.sk02_notification_service.dto.archived.ANFilterDto;
import com.sk02.sk02_notification_service.dto.archived.ArchivedNotificationDto;

import java.util.List;

public interface ArchivedNotificationService {

    void createArchivedNotification(ArchivedNotification archivedNotification);
    List<ArchivedNotificationDto> getNotifications(ANFilterDto anFilterDto, String authorization);
}
