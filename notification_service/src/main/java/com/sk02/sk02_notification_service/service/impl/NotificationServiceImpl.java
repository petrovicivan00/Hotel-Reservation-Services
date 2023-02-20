package com.sk02.sk02_notification_service.service.impl;

import com.sk02.sk02_notification_service.domain.Notification;
import com.sk02.sk02_notification_service.dto.notification.NotificationCreateDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationDto;
import com.sk02.sk02_notification_service.dto.notification.NotificationUpdateDto;
import com.sk02.sk02_notification_service.exception.NotFoundException;
import com.sk02.sk02_notification_service.mapper.NotificationMapper;
import com.sk02.sk02_notification_service.repository.NotificationRepository;
import com.sk02.sk02_notification_service.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private static final String notFoundException = "Notification with given id doesn't exist";

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationDto createNotification(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationCreateDtoToNotification(notificationCreateDto);
        return notificationMapper.notificationToNotificationDto(notificationRepository.save(notification));
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationUpdateDto notificationUpdateDto) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundException));
        notificationMapper.updateNotification(notification, notificationUpdateDto);

        return notificationMapper.notificationToNotificationDto(notificationRepository.save(notification));
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll().stream().map(notificationMapper::notificationToNotificationDto).collect(Collectors.toList());
    }
}
