package org.project.gongsamo.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.gongsamo.domain.Users;
import org.project.gongsamo.dto.TokenInfo;
import org.project.gongsamo.dto.UserAuthRequestDto;
import org.project.gongsamo.global.JwtTokenProvider;
import org.project.gongsamo.repository.UsersQueryRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UsersQueryRepository usersQueryRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Users saveUser(UserAuthRequestDto userAuthRequestDto) throws IllegalStateException{
        Users user = Users.createUser(userAuthRequestDto, passwordEncoder);

        validateDuplicate(user);

        return usersQueryRepository.save(user);
    }

    private void validateDuplicate(Users user) throws IllegalStateException {
        Optional<Users> optionalUsers = usersQueryRepository.findByEmailOrNickName(user);

        if (user.getEmail().equals(optionalUsers.map(Users::getEmail).orElse(""))){
            throw new IllegalStateException("DUPLICATED_EMAIL");
        }
        if (user.getNickname().equals(optionalUsers.map(Users::getNickname).orElse(""))) {
            throw new IllegalStateException("DUPLICATED_NICKNAME");
        }
    }



    @Transactional
    public TokenInfo login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    public TokenInfo reIssueAccessToken(HttpServletRequest request){
        String refreshToken = jwtTokenProvider.resolveToken(request);
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateAccessToken(authentication);

        return tokenInfo;
    }


}
