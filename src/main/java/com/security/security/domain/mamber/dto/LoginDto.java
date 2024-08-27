package com.security.security.domain.mamber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userId;
    private String password;
    private String accessToken;
    private String refreshToken;

    public static LoginDto createTokenLoginResponseDto(MemberDto memberDto, String accessToken, String refreshToken) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(memberDto.getUserId());
        loginDto.setPassword(memberDto.getPassword());
        loginDto.setAccessToken(accessToken);
        loginDto.setRefreshToken(refreshToken);
        return loginDto;
    }
}
