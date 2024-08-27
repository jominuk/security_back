package com.security.security.domain.list.entity;

import com.security.security.domain.list.dto.ListDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "List")
public class ListEntity {

    /**
     * 회원 번호(PK)
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "createDate", nullable = false)
    private LocalDate createDate;

    public ListDto toValueObject() {
        return new ListDto(
                this.getId(),
                this.getTitle(),
                this.getContent(),
                this.getCreateDate()
        );
    }
}
