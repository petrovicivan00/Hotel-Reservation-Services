package com.sk02.sk02_notification_service.repository;

import com.sk02.sk02_notification_service.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findNotificationByType(String type);
}
