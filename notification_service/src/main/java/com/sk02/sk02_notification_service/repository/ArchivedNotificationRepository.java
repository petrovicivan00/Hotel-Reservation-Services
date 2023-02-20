package com.sk02.sk02_notification_service.repository;

import com.sk02.sk02_notification_service.domain.ArchivedNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ArchivedNotificationRepository extends JpaRepository<ArchivedNotification, Long> {

    List<ArchivedNotification> findArchivedNotificationByEmail(String email);
    List<ArchivedNotification> findArchivedNotificationByEmailAndType(String email, String type);
    List<ArchivedNotification> findArchivedNotificationByEmailAndCreatedBetween(String email, Date startDate, Date endDate);
    List<ArchivedNotification> findArchivedNotificationByEmailAndTypeAndCreatedBetween(String email, String type, Date startDate, Date endDate);

    List<ArchivedNotification> findArchivedNotificationByType(String type);
    List<ArchivedNotification> findArchivedNotificationByCreatedBetween(Date startDate, Date endDate);
    List<ArchivedNotification> findArchivedNotificationByTypeAndCreatedBetween(String type, Date startDate, Date endDate);
}
