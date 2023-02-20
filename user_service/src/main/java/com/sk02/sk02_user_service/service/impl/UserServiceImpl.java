package com.sk02.sk02_user_service.service.impl;

import com.sk02.sk02_user_service.domain.ClientAttributes;
import com.sk02.sk02_user_service.domain.ManagerAttributes;
import com.sk02.sk02_user_service.domain.User;
import com.sk02.sk02_user_service.dto.attributes.ManagerAttributesDto;
import com.sk02.sk02_user_service.dto.token.TokenRequestDto;
import com.sk02.sk02_user_service.dto.token.TokenResponseDto;
import com.sk02.sk02_user_service.dto.user.*;
import com.sk02.sk02_user_service.exception.NotEnabledException;
import com.sk02.sk02_user_service.exception.NotFoundException;
import com.sk02.sk02_user_service.mapper.ManagerAttributesMapper;
import com.sk02.sk02_user_service.mapper.UserMapper;
import com.sk02.sk02_user_service.repository.ManagerAttributesRepository;
import com.sk02.sk02_user_service.repository.UserRepository;
import com.sk02.sk02_user_service.security.service.TokenService;
import com.sk02.sk02_user_service.service.NotificationService;
import com.sk02.sk02_user_service.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String notFoundMessageId = "User with given ID not found!";
    private static final String notFoundManagerAttributes = "Manager attributes with given hotel name not found!";
    private static final String notFoundMessageEmailAndPassword = "User with given email and password not found!";
    private static final String userNotActivated = "User profile not activated!";

    private final UserMapper userMapper;
    private final ManagerAttributesMapper managerAttributesMapper;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final NotificationService notificationService;
    private ManagerAttributesRepository managerAttributesRepository;

    public UserServiceImpl(UserMapper userMapper, ManagerAttributesMapper managerAttributesMapper, UserRepository userRepository, TokenService tokenService, NotificationService notificationService, ManagerAttributesRepository managerAttributesRepository) {
        this.userMapper = userMapper;
        this.managerAttributesMapper = managerAttributesMapper;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.notificationService = notificationService;
        this.managerAttributesRepository = managerAttributesRepository;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository.findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword()).orElseThrow(() -> new NotFoundException(notFoundMessageEmailAndPassword));

        if(!user.isEnabled()){
            throw new NotEnabledException(userNotActivated);
        }

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().name());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());

        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper :: userToUserDto);
    }

    @Override
    public UserDto createClient(UserClientCreateDto userClientCreateDto) {
        User user = userMapper.userClientCreateDtoToUser(userClientCreateDto);
        user.setEnabled(false);

        ClientAttributes clientAttributes = new ClientAttributes();

        clientAttributes.setPassportNumber(userClientCreateDto.getPassportNumber());
        clientAttributes.setReservationNumber(0);
        user.setClientAttributes(clientAttributes);

        UserDto userDto = userMapper.userToUserDto(userRepository.save(user));
        notificationService.registerNotification(userDto);

        return userDto;
    }

    @Override
    public UserDto createManager(UserManagerCreateDto userManagerCreateDto) {
        User user = userMapper.userManagerCreateDtoToUser(userManagerCreateDto);
        user.setEnabled(false);

        ManagerAttributes managerAttributes = new ManagerAttributes();

        managerAttributes.setHotelName(userManagerCreateDto.getHotelName());
        managerAttributes.setEmploymentDate(new Date());
        user.setManagerAttributes(managerAttributes);

        UserDto userDto = userMapper.userToUserDto(userRepository.save(user));
        notificationService.registerNotification(userDto);

        return userDto;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new NotFoundException("User with given username - \"" + username + "\" not found!"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId)));
    }

    @Override
    public ManagerAttributesDto getManagerAttributesByManagerId(Long managerId) {
        User manager = userRepository.findById(managerId).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        return managerAttributesMapper.managerAttributesToManagerAttributesDto(manager.getManagerAttributes());
    }

    @Override
    public UserDto updateUserClient(String authorization, UserClientUpdateDto userClientUpdateDto) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        Long id = claims.get("id", Long.class);

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        userMapper.updateClientUser(userClientUpdateDto, user);

        UserDto userDto = userMapper.userToUserDto(userRepository.save(user));
        if (userClientUpdateDto.getPassword() != null)
            notificationService.resetPasswordNotification(userDto);

        return userDto;
    }

    @Override
    public UserDto updateUserManager(String authorization, UserManagerUpdateDto userManagerUpdateDto) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        Long id = claims.get("id", Long.class);

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        userMapper.updateManagerUser(userManagerUpdateDto, user);

        UserDto userDto = userMapper.userToUserDto(userRepository.save(user));
        if (userManagerUpdateDto.getPassword() != null)
            notificationService.resetPasswordNotification(userDto);

        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void activateUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public UserDto findManagerByHotelName(String hotelName) {
        ManagerAttributes managerAttributes = managerAttributesRepository.findManagerAttributesByHotelName(hotelName).orElseThrow(() -> new NotFoundException(notFoundManagerAttributes));
        return userMapper.userToUserDto(managerAttributes.getUser());
    }

}
