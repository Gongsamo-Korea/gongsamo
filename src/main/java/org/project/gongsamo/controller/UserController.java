package org.project.gongsamo.controller;

import lombok.RequiredArgsConstructor;
import org.project.gongsamo.domain.Users;
import org.project.gongsamo.repository.UsersQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsersQueryRepository usersQueryRepository;

    @GetMapping("/user/{userId}")
    public String user(@PathVariable("userId") Long userId) {
        Users user = usersQueryRepository.findOneById(userId);
        return user.toString();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello from gongsamo in dev";
    }
}
