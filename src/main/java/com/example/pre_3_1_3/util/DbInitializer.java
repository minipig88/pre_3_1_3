package com.example.pre_3_1_3.util;

import com.example.pre_3_1_3.dao.RoleDao;
import com.example.pre_3_1_3.dao.UserDao;
import com.example.pre_3_1_3.model.Role;
import com.example.pre_3_1_3.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DbInitializer {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public void initRepo() {
        roleDao.save(new Role("ADMIN"));
        roleDao.save(new Role("USER"));

        Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(roleDao.findByName("ADMIN"));
        rolesAdmin.add(roleDao.findByName("USER"));

        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(roleDao.findByName("USER"));

        userDao.save(new User("admin@gmail.com", passwordEncoder.encode("1"),
                "admin", "admin", 99, rolesAdmin));
        userDao.save(new User("user@gmail.com", passwordEncoder.encode("1"),
                "user", "user", 50, rolesUser));
    }
}
