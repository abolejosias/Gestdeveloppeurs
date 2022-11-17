/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.repository;

import com.frontui.model.Privileges;
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
public interface PrivilegesRepository extends JpaRepository<Privileges, Integer>{
    Privileges findByName(@Param("name") String name);
    
    @Query("select t from Privileges t where t.name LIKE CONCAT('%',:name,'%') ")
    List<Privileges> findallprivilege(@Param("name") String name);
    
    List<Privileges> findByNameContaining(@Param("name") String name);
    
    @Query("select t from Privileges t ORDER BY t.id DESC")
    List<Privileges> getallprivilege();
    
     @Query("select t from Privileges t  where t.idprivilegelevel.id = :idprivilegelevel ORDER BY t.id ASC")
    List<Privileges> getallprivilegeByPrivilegeLevel(@Param("idprivilegelevel") Integer idprivilegelevel);
}
