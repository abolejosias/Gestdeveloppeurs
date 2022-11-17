/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.repository;

import com.mmenu.models.SousMenuActions;
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
public interface SousmenuactionsRepository  extends JpaRepository<SousMenuActions, Integer>{
    
    @Query("select t FROM SousMenuActions t where t.idsousmenu.id = :idsousmenu AND  t.strStatut = :strStatut ORDER BY t.idactions.intPriority ")
    List<SousMenuActions> getallSousmenuactionsByidMenu(@Param("idsousmenu") Integer idsousmenu,@Param("strStatut") String strStatut);
}
