/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.service;

import com.frontui.model.Role;
import com.frontui.model.User;

/**
 *
 * @author hp i3
 */
public interface Userservice {
    User findByUsername(String username);
    Role findRoleByID(Integer id);
    User CreateUser(String first_name,String last_name);
}
