/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.repository;


import com.mgestionnaire.models.Requesttoparticipate;
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
public interface RequesttoparticipateRepository extends JpaRepository<Requesttoparticipate, Integer>{
    
    
    @Query("select t from Requesttoparticipate t where t.idproject = :idproject AND t.iddeveloper = :iddeveloper")
    List<Requesttoparticipate> findbyIdprojectandIdDeveloper(@Param("idproject") Integer idproject , @Param("iddeveloper") Integer iddeveloper);
    
    @Query("select t from Requesttoparticipate t where t.strStatut = :strStatut ORDER BY t.createdAt DESC")
    List<Requesttoparticipate> getallRequesttoparticipate(@Param("strStatut") String strStatut);
   
}
