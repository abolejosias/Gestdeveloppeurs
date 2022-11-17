/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.service.impl;

import com.frontui.model.Role;
import com.frontui.model.User;
import com.frontui.repository.RoleRepository;
import com.frontui.repository.UserRepository;
import com.frontui.service.Userservice;
import com.frontui.toolkits.commonparameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp i3
 */
@Service("userService")
public class UserSeviceImpl implements Userservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Role findRoleByID(Integer id) {
        Role role = null;
        try {
            role = roleRepository.findById(id).get();
            System.err.println("oRole -----" + role.getRoleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public User CreateUser(String first_name, String last_name) {
        User user = null;
        try {
            String username = first_name + "" + last_name;
            username = username.toLowerCase();
            user = new User();
            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setRoleId(2);
            user.setPassword(commonparameter.password);
            user.setUsername(username);
            user.setEmail(username + "@gmail.com");
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
