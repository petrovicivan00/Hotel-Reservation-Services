package com.sk02.sk02_user_service.service;

import com.sk02.sk02_user_service.dto.rank.RankCreateDto;
import com.sk02.sk02_user_service.dto.rank.RankDto;
import com.sk02.sk02_user_service.dto.rank.RankUpdateDto;
import com.sk02.sk02_user_service.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RankService {

    Page<RankDto> findAll(Pageable pageable);

    RankDto createRank(RankCreateDto rankCreateDto);
    RankDto updateRank(Long id, RankUpdateDto rankUpdateDto);

    RankDto findDiscount(Long id);

    void deleteRank(Long id);
}
