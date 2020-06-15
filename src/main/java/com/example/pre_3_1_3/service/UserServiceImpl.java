package com.example.pre_3_1_3.service;

import com.example.pre_3_1_3.dao.RoleDao;
import com.example.pre_3_1_3.dao.UserDao;
import com.example.pre_3_1_3.dto.UserDto;
import com.example.pre_3_1_3.model.Role;
import com.example.pre_3_1_3.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDao.save(getUserFromDto(userDto));
    }

    @Override
    public void updateUser(UserDto userDto) {
        User userUpd = userDao.findById(userDto.getId());
        if (userDto.getPassword() != null && !userDto.getPassword().equals("")) {
            userUpd.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userUpd.setRoles(getRoleSet(userDto.getRoles()));
        userUpd.setUsername(userDto.getEmail());
        userUpd.setFirstName(userDto.getFirstName());
        userUpd.setLastName(userDto.getLastName());
        userUpd.setAge(userDto.getAge());
        userDao.update(userUpd);
    }

    @Override
    public UserDto findUserById(Long id) {
        return getDtoFromUser(userDao.findById(id));
    }

    @Override
    public UserDto findUserByName(String name) {
        return getDtoFromUser(userDao.findByName(name));
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userDao.findAll()) {
            userDtoList.add(getDtoFromUser(user));
        }
        return userDtoList;
    }

    @Override
    public Set<String> findAllRoleNames() {
        Set<String> setNameRoles = new HashSet<>();
        for (Role role : roleDao.findAll()) {
            setNameRoles.add(role.getRoleName());
        }
        return setNameRoles;
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteUser(UserDto userDto) {
        userDao.delete(getUserFromDto(userDto));
    }

    private User getUserFromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getEmail(), userDto.getPassword(),
                userDto.getFirstName(), userDto.getLastName(), userDto.getAge(),
                getRoleSet(userDto.getRoles()));
    }

    private UserDto getDtoFromUser(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(),
                user.getFirstName(), user.getLastName(), user.getAge(),
                getRoleNameSet(user.getRoles()));
    }

    private Set<String> getRoleNameSet(Set<Role> roleSet) {
        Set<String> roleNameSet = new HashSet<>();
        for (Role role : roleSet) {
            roleNameSet.add(role.getRoleName());
        }
        return roleNameSet;
    }

    private Set<Role> getRoleSet(Set<String> roleNameSet) {
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roleNameSet) {
            roleSet.add(roleDao.findByName(roleName));
        }
        return roleSet;
    }
}
