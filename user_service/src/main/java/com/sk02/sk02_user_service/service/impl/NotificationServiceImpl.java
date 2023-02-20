package com.sk02.sk02_user_service.service.impl;

import com.sk02.sk02_user_service.dto.notification.NotificationTransferDto;
import com.sk02.sk02_user_service.dto.user.UserDto;
import com.sk02.sk02_user_service.helper.MessageHelper;
import com.sk02.sk02_user_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final JmsTemplate jmsTemplate;
    private final MessageHelper messageHelper;
    private final String notificationDestination;

    public NotificationServiceImpl(JmsTemplate jmsTemplate, MessageHelper messageHelper, @Value("${destination.notification}") String notificationDestination) {
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.notificationDestination = notificationDestination;
    }

    @Override
    public void registerNotification(UserDto userDto) {
        NotificationTransferDto notificationTransferDto = new NotificationTransferDto();

        Map<String, String> map = new HashMap<>();
        map.put("%username", userDto.getUsername());
        map.put("%link", "http://localhost:8081/api/users/activate/" + userDto.getId());

        notificationTransferDto.setType("REGISTER");
        notificationTransferDto.setEmail(userDto.getEmail());
        notificationTransferDto.setParameters(map);

        jmsTemplate.convertAndSend(notificationDestination, messageHelper.createTextMessage(notificationTransferDto));
    }

    @Override
    public void resetPasswordNotification(UserDto userDto) {
        NotificationTransferDto notificationTransferDto = new NotificationTransferDto();

        Map<String, String> map = new HashMap<>();
        map.put("%username", userDto.getUsername());

        notificationTransferDto.setType("RESET-PASSWORD");
        notificationTransferDto.setEmail(userDto.getEmail());
        notificationTransferDto.setParameters(map);

        jmsTemplate.convertAndSend(notificationDestination, messageHelper.createTextMessage(notificationTransferDto));
    }
}
