package org.project.gongsamo.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.gongsamo.domain.Users;
import org.project.gongsamo.dto.TokenInfo;
import org.project.gongsamo.dto.UserAuthRequestDto;
import org.project.gongsamo.repository.UsersQueryRepository;
import org.project.gongsamo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UsersQueryRepository usersQueryRepository;

    @Operation(summary = "sign up for a new user", description = "회원가입을 한다.")
    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody UserAuthRequestDto userAuthRequestDto) {
       try{
           Users user = userService.saveUser(userAuthRequestDto);
           TokenInfo tokenInfo = userService.login(userAuthRequestDto.getEmail(), userAuthRequestDto.getPassword());
           return ResponseEntity.status(HttpStatus.OK).body(tokenInfo);
       }
       catch (IllegalStateException e){
           HashMap result = new HashMap();
           if("DUPLICATED_EMAIL".equals(e.getMessage())){
               result.put("MESSAGE", e.getMessage());
           } else if ("DUPLICATED_NICKNAME".equals(e.getMessage())) {
               result.put("MESSAGE", e.getMessage());
           }
           return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
       }
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
    public ResponseEntity<Object> testPing() {

        return ResponseEntity.ok().body("test-response");
    }

}
