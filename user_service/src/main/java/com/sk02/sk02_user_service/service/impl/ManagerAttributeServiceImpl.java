package com.sk02.sk02_user_service.service.impl;

import com.sk02.sk02_user_service.domain.ManagerAttributes;
import com.sk02.sk02_user_service.dto.attributes.HotelNewNameDto;
import com.sk02.sk02_user_service.dto.attributes.ManagerAttributesDto;
import com.sk02.sk02_user_service.exception.NotFoundException;
import com.sk02.sk02_user_service.mapper.ManagerAttributesMapper;
import com.sk02.sk02_user_service.repository.ManagerAttributesRepository;
import com.sk02.sk02_user_service.service.ManagerAttributeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerAttributeServiceImpl implements ManagerAttributeService {

    private static final String notFoundHotelName = "Hotel with given name not found!";

    private final ManagerAttributesRepository managerAttributesRepository;
    private ManagerAttributesMapper managerAttributesMapper;

    public ManagerAttributeServiceImpl(ManagerAttributesRepository managerAttributesRepository, ManagerAttributesMapper managerAttributesMapper) {
        this.managerAttributesRepository = managerAttributesRepository;
        this.managerAttributesMapper = managerAttributesMapper;
    }

    @Override
    public ManagerAttributesDto updateNewName(HotelNewNameDto hotelNewNameDto) {
        ManagerAttributes managerAttributes = managerAttributesRepository.findManagerAttributesByHotelName(hotelNewNameDto.getOldName()).orElseThrow(() -> new NotFoundException(notFoundHotelName));
        managerAttributes.setHotelName(hotelNewNameDto.getNewName());

        return managerAttributesMapper.managerAttributesToManagerAttributesDto(managerAttributesRepository.save(managerAttributes));
    }
}
