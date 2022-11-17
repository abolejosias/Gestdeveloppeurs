/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.repository;

import com.frontui.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp i3
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    @Query("select t from Role t where t.roleName = :roleName")
    Role findbyRoleName(String roleName);
}
