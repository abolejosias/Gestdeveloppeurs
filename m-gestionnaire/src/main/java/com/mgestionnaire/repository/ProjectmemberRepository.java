/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.repository;


import com.mgestionnaire.models.Projectmember;
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
public interface ProjectmemberRepository extends JpaRepository<Projectmember, Integer>{
    
    @Query("select t from Projectmember t where t.idproject = :idproject AND t.strStatut = :strStatut")
    List<Projectmember> findbyIdproject(@Param("idproject") Integer idproject , @Param("strStatut") String strStatut);
    
    
    @Query("select t from Projectmember t where t.idproject = :idproject AND t.strStatut = :strStatut")
    List<Projectmember> getallDevelopperByidProjects(@Param("idproject") Integer idproject,@Param("strStatut") String strStatut);
    
    @Query("select t from Projectmember t where t.idproject = :idproject AND t.iddeveloper = :iddeveloper")
    List<Projectmember> findbyIdprojectandIdDeveloper(@Param("idproject") Integer idproject , @Param("iddeveloper") Integer iddeveloper);
    
    
    /*@Query("select t from SousMenu t where t.strStatus = :strStatus ORDER BY t.intPriority DESC")
    List<SousMenu> getallsousmenu(@Param("strStatus") String strStatus);
    
    @Query("select t from SousMenu t where t.strValue = :strValue AND t.idmenu =:idmenu")
    List<SousMenu> findsousmenu(@Param("strValue") String strValue , @Param("idmenu") Integer idmenu);*/
            
}
