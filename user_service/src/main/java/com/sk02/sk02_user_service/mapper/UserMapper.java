package com.sk02.sk02_user_service.mapper;

import com.sk02.sk02_user_service.domain.User;
import com.sk02.sk02_user_service.domain.enums.Role;
import com.sk02.sk02_user_service.dto.user.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public User userClientCreateDtoToUser(UserClientCreateDto userClientCreateDto) {
        User user = new User();

        user.setRole(Role.CLIENT);
        user.setEnabled(false);

        user.setFirstName(userClientCreateDto.getFirstName());
        user.setLastName(userClientCreateDto.getLastName());
        user.setUsername(userClientCreateDto.getUsername());
        user.setPassword(userClientCreateDto.getPassword());
        user.setEmail(userClientCreateDto.getEmail());
        user.setPhone(userClientCreateDto.getPhone());
        user.setBirthday(userClientCreateDto.getBirthday());

        return user;
    }

    public User userManagerCreateDtoToUser(UserManagerCreateDto userManagerCreateDto) {
        User user = new User();

        user.setRole(Role.MANAGER);
        user.setEnabled(false);

        user.setFirstName(userManagerCreateDto.getFirstName());
        user.setLastName(userManagerCreateDto.getLastName());
        user.setUsername(userManagerCreateDto.getUsername());
        user.setPassword(userManagerCreateDto.getPassword());
        user.setEmail(userManagerCreateDto.getEmail());
        user.setPhone(userManagerCreateDto.getPhone());
        user.setBirthday(userManagerCreateDto.getBirthday());

        return user;
    }

    public void updateClientUser(UserClientUpdateDto userClientUpdateDto, User user) {
        if (userClientUpdateDto.getFirstName() != null)
            user.setFirstName(userClientUpdateDto.getFirstName());

        if (userClientUpdateDto.getLastName() != null)
            user.setLastName(userClientUpdateDto.getLastName());

        if (userClientUpdateDto.getUsername() != null)
            user.setUsername(userClientUpdateDto.getUsername());

        if (userClientUpdateDto.getPassword() != null)
            user.setPassword(userClientUpdateDto.getPassword());

        if (userClientUpdateDto.getEmail() != null)
            user.setEmail(userClientUpdateDto.getEmail());

        if (userClientUpdateDto.getPhone() != null)
            user.setPhone(userClientUpdateDto.getPhone());

        if (userClientUpdateDto.getBirthday() != null)
            user.setBirthday(userClientUpdateDto.getBirthday());

        if (userClientUpdateDto.getPassportNumber() != null)
            user.getClientAttributes().setPassportNumber(userClientUpdateDto.getPassportNumber());
    }

    public void updateManagerUser(UserManagerUpdateDto userManagerUpdateDto, User user) {
        if (userManagerUpdateDto.getFirstName() != null)
            user.setFirstName(userManagerUpdateDto.getFirstName());

        if (userManagerUpdateDto.getLastName() != null)
            user.setLastName(userManagerUpdateDto.getLastName());

        if (userManagerUpdateDto.getUsername() != null)
            user.setUsername(userManagerUpdateDto.getUsername());

        if (userManagerUpdateDto.getPassword() != null)
            user.setPassword(userManagerUpdateDto.getPassword());

        if (userManagerUpdateDto.getEmail() != null)
            user.setEmail(userManagerUpdateDto.getEmail());

        if (userManagerUpdateDto.getPhone() != null)
            user.setPhone(userManagerUpdateDto.getPhone());

        if (userManagerUpdateDto.getBirthday() != null)
            user.setBirthday(userManagerUpdateDto.getBirthday());

        if (userManagerUpdateDto.getHotelName() != null) {
            user.getManagerAttributes().setHotelName(userManagerUpdateDto.getHotelName());
            user.getManagerAttributes().setEmploymentDate(new Date());
        }
    }
}
