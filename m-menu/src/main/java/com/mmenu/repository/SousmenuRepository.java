/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.repository;

import com.mmenu.models.SousMenu;
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
public interface SousmenuRepository extends JpaRepository<SousMenu, Integer>{
    
    @Query("select t from SousMenu t where t.idmenu = :idmenu AND t.strStatus = :strStatus ORDER BY t.intPriority")
    List<SousMenu> findbyIdmenu(@Param("idmenu") Integer idmenu , @Param("strStatus") String strStatus);
    
    @Query("select t from SousMenu t where t.strStatus = :strStatus ORDER BY t.intPriority DESC")
    List<SousMenu> getallsousmenu(@Param("strStatus") String strStatus);
    
    @Query("select t from SousMenu t where t.strValue = :strValue AND t.idmenu =:idmenu")
    List<SousMenu> findsousmenu(@Param("strValue") String strValue , @Param("idmenu") Integer idmenu);
            
}
