package com.security.security.domain.mamber.entity;

import com.security.security.domain.mamber.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Member")
public class MemberEntity {

    /**
     * 회원 번호(PK)
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false)
    private String userId;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "memo", nullable = false)
    private String memo;

    public MemberDto toValueObject() {
        return new MemberDto(
                this.getId(),
                this.getUserId(),
                this.getPassword(),
                this.getMemo()
        );
    }
}
