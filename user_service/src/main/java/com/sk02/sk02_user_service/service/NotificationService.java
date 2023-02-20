package com.sk02.sk02_user_service.service;

import com.sk02.sk02_user_service.dto.user.UserDto;

public interface NotificationService {

    void registerNotification(UserDto userDto);

    void resetPasswordNotification(UserDto userDto);
}
