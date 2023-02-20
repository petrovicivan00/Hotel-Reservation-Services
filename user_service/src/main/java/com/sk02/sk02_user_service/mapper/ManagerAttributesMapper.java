package com.sk02.sk02_user_service.mapper;

import com.sk02.sk02_user_service.domain.ManagerAttributes;
import com.sk02.sk02_user_service.dto.attributes.ManagerAttributesDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerAttributesMapper {

    public ManagerAttributesDto managerAttributesToManagerAttributesDto(ManagerAttributes managerAttributes){
        ManagerAttributesDto managerAttributesDto = new ManagerAttributesDto();

        managerAttributesDto.setId(managerAttributes.getId());
        managerAttributesDto.setHotelName(managerAttributes.getHotelName());
        managerAttributesDto.setEmploymentDate(managerAttributes.getEmploymentDate());

        return managerAttributesDto;
    }
}
