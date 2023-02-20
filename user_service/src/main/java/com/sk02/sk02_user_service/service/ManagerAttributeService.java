package com.sk02.sk02_user_service.service;

import com.sk02.sk02_user_service.dto.attributes.HotelNewNameDto;
import com.sk02.sk02_user_service.dto.attributes.ManagerAttributesDto;

public interface ManagerAttributeService {

    ManagerAttributesDto updateNewName(HotelNewNameDto hotelNewNameDto);
}
