package com.sk02.sk02_user_service.service.impl;

import com.sk02.sk02_user_service.domain.Rank;
import com.sk02.sk02_user_service.domain.User;
import com.sk02.sk02_user_service.dto.rank.RankCreateDto;
import com.sk02.sk02_user_service.dto.rank.RankDto;
import com.sk02.sk02_user_service.dto.rank.RankUpdateDto;
import com.sk02.sk02_user_service.dto.user.UserDto;
import com.sk02.sk02_user_service.exception.NotFoundException;
import com.sk02.sk02_user_service.exception.RankException;
import com.sk02.sk02_user_service.mapper.RankMapper;
import com.sk02.sk02_user_service.repository.RankRepository;
import com.sk02.sk02_user_service.repository.UserRepository;
import com.sk02.sk02_user_service.service.RankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RankServiceImpl implements RankService {

    private static final String rankNotFound = "Rank with given ID not found!";
    private static final String userNotFound = "User with given ID not found!";
    private static final String discountNotFound = "Discount for given user not found";

    private final RankRepository rankRepository;
    private final UserRepository userRepository;
    private final RankMapper rankMapper;

    public RankServiceImpl(RankRepository rankRepository, UserRepository userRepository, RankMapper rankMapper) {
        this.rankRepository = rankRepository;
        this.userRepository = userRepository;
        this.rankMapper = rankMapper;
    }

    @Override
    public Page<RankDto> findAll(Pageable pageable) {
        return rankRepository.findAll(pageable).map(rankMapper::rankToRankDto);
    }

    @Override
    public RankDto createRank(RankCreateDto rankCreateDto) {
        Rank rank = rankMapper.rankCreateDtoToRank(rankCreateDto);
        return rankMapper.rankToRankDto(rankRepository.save(rank));
    }

    @Override
    public RankDto updateRank(Long id, RankUpdateDto rankUpdateDto) {
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new NotFoundException(rankNotFound));
        rankMapper.rankUpdateDtoToRank(rank, rankUpdateDto);
        return rankMapper.rankToRankDto(rankRepository.save(rank));
    }

    @Override
    public RankDto findDiscount(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(userNotFound));
        Rank rank = rankRepository.findRankByNumberOfReservation(user.getClientAttributes().getReservationNumber()).orElseThrow(() -> new RankException(discountNotFound));

        return rankMapper.rankToRankDto(rank);
    }

    @Override
    public void deleteRank(Long id) {
        rankRepository.deleteById(id);
    }
}
