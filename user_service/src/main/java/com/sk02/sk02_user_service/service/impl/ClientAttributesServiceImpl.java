package com.sk02.sk02_user_service.service.impl;

import com.sk02.sk02_user_service.domain.User;
import com.sk02.sk02_user_service.dto.user.UserDto;
import com.sk02.sk02_user_service.exception.NotFoundException;
import com.sk02.sk02_user_service.repository.UserRepository;
import com.sk02.sk02_user_service.service.ClientAttributesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientAttributesServiceImpl implements ClientAttributesService {

    private static final String userNotFound = "User with given id not found!";

    private final UserRepository userRepository;

    public ClientAttributesServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateClientReservations(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userNotFound));
        user.getClientAttributes().setReservationNumber(user.getClientAttributes().getReservationNumber() + 1);
        userRepository.save(user);
    }

    @Override
    public void updateClientCancellation(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new NotFoundException(userNotFound));

        user.getClientAttributes().setReservationNumber(user.getClientAttributes().getReservationNumber() - 1);
        userRepository.save(user);
    }
}
