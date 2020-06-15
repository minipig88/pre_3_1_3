package com.example.pre_3_1_3.service;

import com.example.pre_3_1_3.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    UserDto findUserById(Long id);
    UserDto findUserByName(String name);
    List<UserDto> findAllUsers();
    Set<String> findAllRoleNames();
    void deleteUserById(Long id);
    void deleteUser(UserDto userDto);
}
