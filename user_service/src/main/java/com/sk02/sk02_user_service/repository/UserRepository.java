package com.sk02.sk02_user_service.repository;

import com.sk02.sk02_user_service.domain.User;
import com.sk02.sk02_user_service.dto.user.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmailAndPassword(String email, String password);

}
