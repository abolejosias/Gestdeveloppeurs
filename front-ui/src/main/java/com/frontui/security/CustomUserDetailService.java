/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.security;

import com.frontui.model.Role;
import com.frontui.model.User;
import com.frontui.service.Userservice;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp i3
 */
@Service("CustomUserDetailService")
public class CustomUserDetailService implements UserDetailsService{
    
    @Autowired
    Userservice userservice;

 @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("username ------------" + username);
        if (username.trim().isEmpty()) {
            throw new UsernameNotFoundException("username is empty");
        }

         User user = userservice.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), this.getGrantedAuthorities(user));
    }

    
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> lstgrantedAuthoritys = new ArrayList<>();
        try {
            Role role = userservice.findRoleByID(user.getRoleId());
            lstgrantedAuthoritys.add(new SimpleGrantedAuthority(role.getRoleName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstgrantedAuthoritys;
    }
    
}
