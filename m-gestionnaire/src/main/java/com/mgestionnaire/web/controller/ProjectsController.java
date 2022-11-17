/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.web.controller;

import com.mgestionnaire.models.Developer;
import com.mgestionnaire.models.DeveloperInOutProjects;
import com.mgestionnaire.models.Projects;
import com.mgestionnaire.models.Requesttoparticipate;
import com.mgestionnaire.service.ProjectService;
import com.mgestionnaire.toolkits.commonparameter;
import com.mgestionnaire.web.exception.MessageNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp i3
 */
@RestController
public class ProjectsController {
    
    @Autowired
    ProjectService projectService;
    
    @PostMapping(value = "/createprojects")
    public List<Object> createprojects(@RequestParam String name, @RequestParam String description, @RequestParam String created_by) {
        System.err.println("name  ********************************************" + name);
        projectService.CreateProjects(name, description, created_by);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }
    
    @PostMapping(value = "/udpateprojects")
    public List<Object> udpateprojects(@RequestParam Integer Idprojects, @RequestParam String description, @RequestParam String updated_by) {
        System.err.println("description  ********************************************" +description);
        projectService.UpdateProjects(Idprojects, description, updated_by);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }
    
    

    @PostMapping(value = "/deleteprojects")
    public List<Object> deleteprojects(@RequestParam Integer idproject) {
        projectService.DeleteProjects(idproject);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }

    @GetMapping(value = "/listprojects")
    public List<Projects> listProjects() {
        List<Projects> listProjects = projectService.getListProjects(commonparameter.statut_enable);
        return listProjects;
    }

    @GetMapping(value = "/findprojects/{id}")
    public Projects findprojectsbyid(@PathVariable Integer id) {
        return projectService.findByIdProjects(id);
    }

    @PostMapping(value = "/createprojectmember")
    public List<Object> createprojectmember(@RequestParam Integer idProjects, @RequestParam Integer idDeveloper, @RequestParam String created_by) {
        System.err.println("idDeveloper  ********************************************" + idDeveloper);
        projectService.CreateProjectmember(idProjects, idDeveloper, created_by);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }

    @PostMapping(value = "/deleteprojectmember")
    public List<Object> deleteprojectmember(@RequestParam Integer idProjects, @RequestParam Integer idDeveloper) {
        projectService.DeleteProjectmember(idProjects, idDeveloper);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }
 

    @GetMapping(value = "/listDeveloperByIdProject")
    public List<Developer> listDeveloperByIdProject(@RequestParam Integer idprojects) {
        List<Developer> listDeveloper = projectService.getListDeveloperByIdProject(idprojects);
        return listDeveloper;
    }
  
     
     @GetMapping(value = "/listDeveloperAutho_UnAutho_To_Projects")
     public List<DeveloperInOutProjects> listDeveloperAutho_UnAutho_To_Projects(@RequestParam Integer idProjects) {
        List<DeveloperInOutProjects> listDeveloperInOutProjects = projectService.getAllDeveloperAutho_UnAutho_To_Projects(String.valueOf(idProjects));
        return listDeveloperInOutProjects;
    }
     
      @GetMapping(value = "/listotherprojects")
      public List<Projects> listotherprojects(@RequestParam Integer iddeveloper) {
        List<Projects> listProjects = projectService.getListOtherProjects(iddeveloper,commonparameter.statut_enable);
        return listProjects;
    }
    
     @GetMapping(value = "/listmyprojects")
      public List<Projects> listmyprojects(@RequestParam Integer iddeveloper) {
        List<Projects> listProjects = projectService.getListMyProjects(iddeveloper,commonparameter.statut_enable);
        return listProjects;
    }
    
      
    @PostMapping(value = "/createrequesttoparticipate")
    public List<Object> createrequesttoparticipate(@RequestParam Integer idProjects, @RequestParam Integer idDeveloper, @RequestParam String created_by) {
        System.err.println("idDeveloper  ********************************************" + idDeveloper);
        projectService.Createrequesttoparticipate(idProjects, idDeveloper, created_by);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }
    
    @PostMapping(value = "/validatedrequesttoparticipate")
    public List<Object> validatedrequesttoparticipate(@RequestParam Integer idrequesttoparticipate,@RequestParam String created_by) {
        System.err.println("idrequesttoparticipate  ********************************************" + idrequesttoparticipate);
        projectService.Validedrequesttoparticipate(idrequesttoparticipate, created_by);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }
    
    @PostMapping(value = "/rejectrequesttoparticipate")
    public List<Object> rejectrequesttoparticipate(@RequestParam Integer idrequesttoparticipate) {
        System.err.println("idrequesttoparticipate  ********************************************" + idrequesttoparticipate);
        projectService.Rejectrequesttoparticipate(idrequesttoparticipate);
        return MessageNotFoundException.getMessageException(projectService.getMessage(), projectService.getDetailmessage());
    }
    
    
    @GetMapping(value = "/listrequesttoparticipate")
      public List<Requesttoparticipate> listrequesttoparticipate() {
        List<Requesttoparticipate> listRequesttoparticipate = projectService.getRequesttoparticipate(commonparameter.statut_enattente);
        return listRequesttoparticipate;
    }
    
}
