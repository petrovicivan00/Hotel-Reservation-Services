package com.sk02.sk02_notification_service.mail;

import com.sk02.sk02_notification_service.domain.ArchivedNotification;
import com.sk02.sk02_notification_service.domain.Notification;
import com.sk02.sk02_notification_service.dto.notification.NotificationTransferDto;
import com.sk02.sk02_notification_service.exception.NotFoundException;
import com.sk02.sk02_notification_service.repository.ArchivedNotificationRepository;
import com.sk02.sk02_notification_service.repository.NotificationRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Date;
import java.util.Map;

@Component
@Transactional
public class EmailListener {

    private static final String notFoundNotification = "Notification with given type doesn't exist";

    private final MessageHelper messageHelper;
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;
    private final ArchivedNotificationRepository archivedNotificationRepository;

    public EmailListener(MessageHelper messageHelper, EmailService emailService, NotificationRepository notificationRepository, ArchivedNotificationRepository archivedNotificationRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.archivedNotificationRepository = archivedNotificationRepository;
    }

    @JmsListener(destination = "${destination.notification}", concurrency = "5-10")
    public void sendNotification(Message message) throws JMSException {
        NotificationTransferDto notificationTransferDto = messageHelper.getMessage(message, NotificationTransferDto.class);
        Notification notification = notificationRepository.findNotificationByType(notificationTransferDto.getType()).orElseThrow(() -> new NotFoundException(notFoundNotification));

        String messageToSend = notification.getMessage();
        for (Map.Entry<String,String> entry : notificationTransferDto.getParameters().entrySet()) {
            messageToSend = messageToSend.replace(entry.getKey(), entry.getValue());
        }

        emailService.sendEmail(notificationTransferDto.getEmail(), notification.getSubject(), messageToSend);
        createArchivedNotification(notificationTransferDto.getType(), notificationTransferDto.getEmail(), notification.getSubject(), messageToSend);
    }

    public void createArchivedNotification(String type, String email, String subject, String message) {
        ArchivedNotification archivedNotification = new ArchivedNotification();

        archivedNotification.setType(type);
        archivedNotification.setEmail(email);
        archivedNotification.setSubject(subject);
        archivedNotification.setMessage(message);
        archivedNotification.setCreated(new Date());

        archivedNotificationRepository.save(archivedNotification);
    }
}
