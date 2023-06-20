package org.project.gongsamo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.project.gongsamo.repository.UsersQueryRepository;
import org.project.gongsamo.service.BannerService;
import org.project.gongsamo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "banner", description = "베너")
public class BannerController {

    private final BannerService bannerService;


    @GetMapping("/banners")
    public ResponseEntity<Object> loadBannerList() {

        return ResponseEntity.ok().body(bannerService.loadBannerList());
    }

}
