package com.security.security.domain.mamber.controller;

import com.security.security.domain.mamber.dto.LoginDto;
import com.security.security.domain.mamber.dto.MemberDto;
import com.security.security.domain.mamber.entity.MemberEntity;
import com.security.security.domain.mamber.service.MemberService;
import com.security.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    /**
     * 사용자 추가
     * */
    @PostMapping("/save")
    public ResponseEntity<MemberDto> memberSave(
            @RequestBody MemberDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.memberSave(dto));
    }

    /**
     * 로그인
     */
    @PostMapping("/")
    public ResponseEntity<LoginDto> memberLogin(@RequestBody LoginDto dto) {
        try {
            LoginDto responseDto = memberService.memberLogin(dto.getUserId(), dto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
