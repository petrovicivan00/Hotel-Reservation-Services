package com.sk02.sk02_user_service.controller;

import com.sk02.sk02_user_service.dto.rank.RankCreateDto;
import com.sk02.sk02_user_service.dto.rank.RankDto;
import com.sk02.sk02_user_service.dto.rank.RankUpdateDto;
import com.sk02.sk02_user_service.dto.user.UserDto;
import com.sk02.sk02_user_service.security.CheckSecurity;
import com.sk02.sk02_user_service.service.RankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ranks")
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<Page<RankDto>> findAll(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(rankService.findAll(PageRequest.of(0, 20)), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<RankDto> createRank(@RequestHeader("Authorization") String authorization, @RequestBody @Valid RankCreateDto rankCreateDto) {
        return new ResponseEntity<>(rankService.createRank(rankCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<RankDto> updateRank(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, @RequestBody RankUpdateDto rankUpdateDto) {
        return new ResponseEntity<>(rankService.updateRank(id, rankUpdateDto), HttpStatus.OK);
    }

    @GetMapping("/discount/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<RankDto> findDiscount(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(rankService.findDiscount(id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<HttpStatus> deleteRank(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        rankService.deleteRank(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
