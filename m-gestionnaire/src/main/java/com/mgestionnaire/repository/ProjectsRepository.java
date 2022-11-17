/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.repository;



import com.mgestionnaire.models.Projects;
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
public interface ProjectsRepository extends JpaRepository<Projects, Integer>{
    
    @Query("select t from Projects t where t.name = :name")
    List<Projects> findProjects(@Param("name") String name);
    
    @Query("select t from Projects t where t.strStatut = :strStatut ORDER BY t.name")
    List<Projects> getallProjects(@Param("strStatut") String strStatut);
   
    
    
    
}
