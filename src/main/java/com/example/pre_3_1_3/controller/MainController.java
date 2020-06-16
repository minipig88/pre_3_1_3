package com.example.pre_3_1_3.controller;

import com.example.pre_3_1_3.dto.UserDto;
import com.example.pre_3_1_3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        UserDto currentUser = userService.findUserByName(principal.getName());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
