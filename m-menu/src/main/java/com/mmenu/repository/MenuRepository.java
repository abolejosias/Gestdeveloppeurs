/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.repository;

import com.mmenu.models.Menu;
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
public interface MenuRepository extends JpaRepository<Menu, Integer>{
    
    @Query("select t from Menu t  where t.idmodule.id =:idmodule AND t.strStatus = :strStatus ORDER BY t.intPriority")
    List<Menu> findbyidmodule(@Param("idmodule") Integer idmodule,@Param("strStatus") String strStatus);
    
    @Query("select t from Menu t where t.strStatus = :strStatus ORDER BY t.intPriority")
    List<Menu> getallmenu(@Param("strStatus") String strStatus);
    
    
    
}
