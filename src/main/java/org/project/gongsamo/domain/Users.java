package org.project.gongsamo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.project.gongsamo.domain.Enum.UserRole;
import org.project.gongsamo.dto.UserAuthRequestDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;



@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Table(name = "USER")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true , columnDefinition = "VARCHAR(40) COMMENT '비밀번호'")
    private String email;


    @Column(nullable = false,columnDefinition = "VARCHAR(60) COMMENT '비밀번호'")
    private String password;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(20) COMMENT '닉네임'")
    private String nickname;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "VARCHAR(10) COMMENT '역할'")
    private UserRole role;

    @CreatedDate
    @Column(nullable = false,columnDefinition = "datetime DEFAULT now() COMMENT '등록일'")
    private LocalDateTime registerDate;

    public static Users createUser(UserAuthRequestDto userAuthRequestDto, PasswordEncoder passwordEncoder) {
        Users users = Users.builder()
                .email(userAuthRequestDto.getEmail())
                .password(passwordEncoder.encode(userAuthRequestDto.getPassword()))
                .nickname(userAuthRequestDto.getNickname())
                .role(UserRole.USER)
                .build();

        return users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
        collection.add(()->{ return this.role.name();});
        return collection;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
