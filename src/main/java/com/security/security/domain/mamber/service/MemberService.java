package com.security.security.domain.mamber.service;

import com.security.security.domain.mamber.dto.LoginDto;
import com.security.security.domain.mamber.dto.MemberDto;
import com.security.security.domain.mamber.entity.MemberEntity;
import com.security.security.domain.mamber.repository.MemberRepository;
import com.security.security.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public MemberDto memberSave(MemberDto dto) {
        final var entity = new MemberEntity();

        Optional<MemberEntity> existingMember = memberRepository.findByUserId(dto.getUserId());
        if (existingMember.isPresent()) {
            throw  new RuntimeException("아이디가 이미 존재합니다. 확인해주세요");
        }

        entity.setUserId(dto.getUserId());
        entity.setPassword(dto.getPassword());
        entity.setMemo(dto.getMemo());

        return memberRepository.save(entity).toValueObject();
    }

    @Transactional
    public LoginDto memberLogin(String userId, String password) {
        MemberEntity memberEntity = memberRepository.findByUserIdAndPassword(userId, password);

        if (memberEntity == null) {
            throw new RuntimeException("아이디와 비밀번호를 확인해주세요");
        }

        // 사용자 정보를 DTO로 변환
        MemberDto memberDto = memberEntity.toValueObject();

        // 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(memberDto);
        String refreshToken = jwtTokenProvider.generateRefreshToken(memberDto);

        System.out.println("액세스 토큰 : " + accessToken);
        System.out.println("리프레쉬 토큰 : " + refreshToken);

        // 토큰과 사용자 정보를 포함한 LoginDto 생성
        return LoginDto.createTokenLoginResponseDto(memberDto, accessToken, refreshToken);
    }
}
