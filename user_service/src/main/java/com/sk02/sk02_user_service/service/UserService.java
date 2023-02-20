package com.sk02.sk02_user_service.service;

import com.sk02.sk02_user_service.dto.attributes.ManagerAttributesDto;
import com.sk02.sk02_user_service.dto.token.TokenRequestDto;
import com.sk02.sk02_user_service.dto.token.TokenResponseDto;
import com.sk02.sk02_user_service.dto.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    Page<UserDto> findAll(Pageable pageable);

    UserDto createClient(UserClientCreateDto userClientCreateDto);
    UserDto createManager(UserManagerCreateDto userManagerCreateDto);

    UserDto getUserByUsername(String username);
    UserDto getUserById(Long id);

    ManagerAttributesDto getManagerAttributesByManagerId(Long managerId);

    UserDto updateUserClient(String authorization, UserClientUpdateDto userClientUpdateDto);
    UserDto updateUserManager(String authorization, UserManagerUpdateDto userManagerUpdateDto);

    void deleteUser(Long id);
    void activateUser(Long id);

    UserDto findManagerByHotelName(String hotelName);
}
