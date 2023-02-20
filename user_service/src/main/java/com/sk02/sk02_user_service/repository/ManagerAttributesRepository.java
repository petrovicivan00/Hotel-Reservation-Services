package com.sk02.sk02_user_service.repository;

import com.sk02.sk02_user_service.domain.ManagerAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerAttributesRepository extends JpaRepository<ManagerAttributes, Long> {

    Optional<ManagerAttributes> findManagerAttributesByHotelName(String hotelName);
}
