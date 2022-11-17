/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.service;

import com.mgestionnaire.models.Developer;
import com.mgestionnaire.models.DeveloperInOutProjects;
import com.mgestionnaire.models.Projectmember;
import com.mgestionnaire.models.Projects;
import com.mgestionnaire.models.Requesttoparticipate;
import com.mmenu.toolkits.Iservicemsg;
import java.util.List;

/**
 *
 * @author hp i3
 */
public interface ProjectService extends Iservicemsg{
    
    Boolean CreateProjects(String name,String description,String created_by);
    Boolean UpdateProjects(Integer IdProjects,String description,String updated_by);
    Boolean DeleteProjects(Integer IdProjects);
    List<Projects> getListProjects(String str_statut);
    Boolean CheckifProjectExist(String name);
    Boolean CreateProjectmember(Integer idproject,Integer iddeveloper,String created_by);
    Boolean DeleteProjectmember(Integer idproject,Integer iddeveloper);
    List<Projectmember> getListProjectmember(Integer idproject);
    List<Developer> getListDeveloperByIdProject(Integer idproject);
    List<DeveloperInOutProjects> getAllDeveloperAutho_UnAutho_To_Projects(String idProject);
    Boolean isExistwithProjectsDeveloper(Integer idDevelopper,Integer idProject);
    void UpdateDeveloperNumber(Integer IdProjects,Boolean str_etat);
    Projects findByIdProjects(Integer idProject);
    String getSkillsToString(Integer idDevelopper);
    List<Projects> getListMyProjects(Integer idDevelopper,String str_statut);
    List<Projects> getListOtherProjects(Integer idDevelopper,String str_statut);
    
    Boolean Createrequesttoparticipate(Integer idproject,Integer iddeveloper,String created_by);
    Boolean Validedrequesttoparticipate(Integer idrequesttoparticipate,String created_by);
    Boolean Rejectrequesttoparticipate(Integer idrequesttoparticipate);
    List<Requesttoparticipate> getRequesttoparticipate(String str_statut);
    
}
