package org.project.gongsamo.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.gongsamo.domain.Users;
import org.project.gongsamo.dto.BannerDto;
import org.project.gongsamo.dto.TokenInfo;
import org.project.gongsamo.dto.UserAuthRequestDto;
import org.project.gongsamo.global.JwtTokenProvider;
import org.project.gongsamo.repository.BannerQueryRepository;
import org.project.gongsamo.repository.UsersQueryRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BannerService {

    private final BannerQueryRepository bannerQueryRepository;

    public List<BannerDto> loadBannerList(){

        return bannerQueryRepository.findAllBanners();
    }



}
