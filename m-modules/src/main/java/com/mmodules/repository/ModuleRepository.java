/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmodules.repository;

import com.mmodules.models.Module;
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
public interface ModuleRepository extends JpaRepository<Module, Integer>{
    
    @Query("select t from Module t where t.strValue = :strValue")
    Module findbyStrValue(@Param("strValue") String strValue);
    
    @Query("select t from Module t where  t.strStatut =:strStatut ORDER BY t.intPriority")
    List<Module> findAllmodule(@Param("strStatut") String strStatut);
    
    
}
