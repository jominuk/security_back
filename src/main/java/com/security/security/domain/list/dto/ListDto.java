package com.security.security.domain.list.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate createDate;
}
