/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.repository;


import com.mgestionnaire.models.DeveloperSkills;
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
public interface DeveloperskillsRepository  extends JpaRepository<DeveloperSkills, Integer>{
    
    @Query("select t FROM DeveloperSkills t where t.iddeveloper = :iddeveloper AND  t.strStatut = :strStatut")
    List<DeveloperSkills> getallDevelopperskillsByidDeveloper(@Param("iddeveloper") Integer iddeveloper,@Param("strStatut") String strStatut);

    @Query("SELECT t from DeveloperSkills t where t.idskills = :idskills and t.iddeveloper = :iddeveloper")
    List<DeveloperSkills> findByidskillsandIdrole(@Param("idskills") Integer idskills , @Param("iddeveloper") Integer iddeveloper);
   

}
