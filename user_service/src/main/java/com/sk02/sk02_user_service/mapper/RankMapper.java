package com.sk02.sk02_user_service.mapper;

import com.sk02.sk02_user_service.domain.Rank;
import com.sk02.sk02_user_service.dto.rank.RankCreateDto;
import com.sk02.sk02_user_service.dto.rank.RankDto;
import com.sk02.sk02_user_service.dto.rank.RankUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class RankMapper {

    public RankDto rankToRankDto(Rank rank) {
        RankDto rankDto = new RankDto();

        rankDto.setId(rank.getId());
        rankDto.setName(rank.getName());
        rankDto.setReservationLimit(rank.getReservationLimit());
        rankDto.setDiscount(rank.getDiscount());

        return rankDto;
    }

    public Rank rankCreateDtoToRank(RankCreateDto rankCreateDto) {
        Rank rank = new Rank();

        rank.setName(rankCreateDto.getName());
        rank.setReservationLimit(rankCreateDto.getReservationLimit());
        rank.setDiscount(rankCreateDto.getDiscount());

        return rank;
    }

    public void rankUpdateDtoToRank(Rank rank, RankUpdateDto rankUpdateDto) {
        if (rankUpdateDto.getName() != null)
            rank.setName(rankUpdateDto.getName());

        if (rankUpdateDto.getReservationLimit() != 0)
            rank.setReservationLimit(rankUpdateDto.getReservationLimit());

        if (rankUpdateDto.getDiscount() != 0)
            rank.setDiscount(rankUpdateDto.getDiscount());
    }
}
