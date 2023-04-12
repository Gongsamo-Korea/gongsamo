package org.project.gongsamo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.gongsamo.domain.Users;
import org.project.gongsamo.dto.TokenInfo;
import org.project.gongsamo.dto.UserAuthRequestDto;
import org.project.gongsamo.repository.UsersQueryRepository;
import org.project.gongsamo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UsersQueryRepository usersQueryRepository;

    @PostMapping("/signUp")
    public TokenInfo signUp(@RequestBody UserAuthRequestDto userAuthRequestDto) {
        Users user = userService.saveUser(userAuthRequestDto);

        TokenInfo tokenInfo = userService.login(userAuthRequestDto.getEmail(), userAuthRequestDto.getPassword());
        return tokenInfo;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserAuthRequestDto userAuthRequestDto) {

        String email = userAuthRequestDto.getEmail();
        String password = userAuthRequestDto.getPassword();

        return ResponseEntity.ok().body(userService.login(email, password));
    }



    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refresh(HttpServletRequest request) {


      return ResponseEntity.ok().body(userService.reIssueAccessToken(request));
    }


    @GetMapping("/testPing")
    public String testPing() {

        return "test-response";
    }

}
