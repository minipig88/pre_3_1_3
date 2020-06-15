package com.example.pre_3_1_3.controller;

import com.example.pre_3_1_3.dto.UserDto;
import com.example.pre_3_1_3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin")
public class AdminRestController {

    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Set<String>> getAllRoles() {
        return new ResponseEntity<>(userService.findAllRoleNames(), HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        userService.saveUser(user);
        return new ResponseEntity<>(userService.findUserByName(user.getEmail()), HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        userService.updateUser(user);
        return new ResponseEntity<>(userService.findUserByName(user.getEmail()), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
