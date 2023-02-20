package com.sk02.sk02_notification_service.service.impl;

import com.sk02.sk02_notification_service.domain.ArchivedNotification;
import com.sk02.sk02_notification_service.dto.archived.ANFilterDto;
import com.sk02.sk02_notification_service.dto.archived.ArchivedNotificationDto;
import com.sk02.sk02_notification_service.mapper.ArchivedNotificationMapper;
import com.sk02.sk02_notification_service.repository.ArchivedNotificationRepository;
import com.sk02.sk02_notification_service.security.service.TokenService;
import com.sk02.sk02_notification_service.service.ArchivedNotificationService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchivedNotificationServiceImpl implements ArchivedNotificationService {

    private final ArchivedNotificationRepository archivedNotificationRepository;
    private final ArchivedNotificationMapper archivedNotificationMapper;
    private final TokenService tokenService;

    public ArchivedNotificationServiceImpl(ArchivedNotificationRepository archivedNotificationRepository, ArchivedNotificationMapper archivedNotificationMapper, TokenService tokenService) {
        this.archivedNotificationRepository = archivedNotificationRepository;
        this.archivedNotificationMapper = archivedNotificationMapper;
        this.tokenService = tokenService;
    }

    @Override
    public void createArchivedNotification(ArchivedNotification archivedNotification) {
        archivedNotificationRepository.save(archivedNotification);
    }

    @Override
    public List<ArchivedNotificationDto> getNotifications(ANFilterDto anFilterDto, String authorization) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        String role = claims.get("role", String.class);

        List<ArchivedNotificationDto> archivedNotifications;

        if (role.equals("ADMIN")) {
            if (anFilterDto.getType() != null && anFilterDto.getStartDate() != null && anFilterDto.getEndDate() != null) {
                archivedNotifications = archivedNotificationRepository.findArchivedNotificationByTypeAndCreatedBetween(anFilterDto.getType(), anFilterDto.getStartDate(), anFilterDto.getEndDate()).stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
            else if (anFilterDto.getStartDate() != null && anFilterDto.getEndDate() != null) {
                archivedNotifications = archivedNotificationRepository.findArchivedNotificationByCreatedBetween(anFilterDto.getStartDate(), anFilterDto.getEndDate()).stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
            else if (anFilterDto.getType() != null) {
                archivedNotifications = archivedNotificationRepository.findArchivedNotificationByType(anFilterDto.getType()).stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
            else {
                archivedNotifications = archivedNotificationRepository.findAll().stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
        }
        else {
            String email = claims.get("email", String.class);

            if (anFilterDto.getType() != null && anFilterDto.getStartDate() != null && anFilterDto.getEndDate() != null) {
                archivedNotifications = archivedNotificationRepository.findArchivedNotificationByEmailAndTypeAndCreatedBetween(email, anFilterDto.getType(), anFilterDto.getStartDate(), anFilterDto.getEndDate()).stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
            else if (anFilterDto.getStartDate() != null && anFilterDto.getEndDate() != null) {
                archivedNotifications = archivedNotificationRepository.findArchivedNotificationByEmailAndCreatedBetween(email, anFilterDto.getStartDate(), anFilterDto.getEndDate()).stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
            else if (anFilterDto.getType() != null) {
                archivedNotifications = archivedNotificationRepository.findArchivedNotificationByEmailAndType(email, anFilterDto.getType()).stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
            else {
                archivedNotifications = archivedNotificationRepository.findArchivedNotificationByEmail(email).stream().map(archivedNotificationMapper::anToAnDto).collect(Collectors.toList());
            }
        }

        return archivedNotifications;
    }
}
