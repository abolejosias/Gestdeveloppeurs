/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.repository;


import com.mgestionnaire.models.Developer;
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
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    @Query("select t from Developer t where t.firstName = :firstName AND t.lastName =:lastName")
    List<Developer> findDeveloper(@Param("firstName") String firstName , @Param("lastName") String lastName);
    
    @Query("select t from Developer t where t.strStatut = :strStatut ORDER BY t.firstName")
    List<Developer> getallDeveloper(@Param("strStatut") String strStatut);
    
    @Query("select t from Developer t where t.iduser = :iduser")
    List<Developer> findDeveloperByIduser(@Param("iduser") Integer iduser);
    
}
