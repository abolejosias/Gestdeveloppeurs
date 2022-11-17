/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.service;


import com.mgestionnaire.models.Developer;
import com.mgestionnaire.models.Skills;
import com.mgestionnaire.models.SkillsInOutDeveloper;
import com.mmenu.toolkits.Iservicemsg;
import java.util.List;

/**
 *
 * @author hp i3
 */
public interface DeveloperService extends Iservicemsg{
    
    Boolean CreateDeveloper(String first_name,String last_name,String created_by,Integer iduser);
    Boolean UpdateDeveloper(Integer idDeveloper, String first_name,String last_name,String updated_by);
    List<Developer> getListDevelopers(String str_statut);
    Boolean DeleteDeveloper(Integer idDeveloper);
    Boolean CreateDeveloperSkills(Integer idDeveloper, Integer idskills, String created_by);
    Boolean DeleteDeveloperSkills(Integer idDeveloperskills);
    Boolean DeleteDeveloperSkills(Integer idDeveloper, Integer idskills);
    Developer findByIdDeveloper(Integer idDeveloper);
    Boolean CheckifDeveloperExist(String first_name,String last_name);
    List<Skills> getListSkillsByIdDeveloper(Integer idDeveloper);
    List<Skills> getListSkills();
    List<SkillsInOutDeveloper> getAllSkillsAutho_UnAutho_To_Developer(String idDeveloper);
    Boolean isExistwithDeveloperSkills(Integer idskills,Integer idDeveloper);
    Developer findByIdDeveloperByIduser(Integer iduser);
    
}
