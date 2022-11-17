/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.repository;

import com.frontui.model.RolePrivileges;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp i3
 */
@Repository
public interface RolePrivilegesRepository extends JpaRepository<RolePrivileges, Integer>{
    
    @Query("SELECT t from RolePrivileges t where t.idroles.id  = :idroles")
    List<RolePrivileges> findByidroles(@Param("idroles") Integer idroles);
    
    @Query("SELECT t from RolePrivileges t where t.idprivileges.id = :idprivilege and t.idroles.id = :idroles")
    List<RolePrivileges> findByidprivilege(@Param("idprivilege") Integer idprivilege , @Param("idroles") Integer idroles);
    
   
}
