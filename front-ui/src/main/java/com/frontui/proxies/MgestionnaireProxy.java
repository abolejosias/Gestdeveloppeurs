/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.proxies;

import com.frontui.beans.DeveloperBean;
import com.frontui.beans.DeveloperInOutProjectsBean;
import com.frontui.beans.ProjectsBean;
import com.frontui.beans.RequesttoparticipateBean;
import com.frontui.beans.SkillsBean;
import com.frontui.beans.SkillsInOutDeveloperBean;
import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hp i3
 */
@FeignClient(name = "m-gestionnaire", decode404 = true)
@RibbonClient(name = "m-gestionnaire")
public interface MgestionnaireProxy {
    
    @PostMapping(value = "/createdeveloper")
    public List<Object> createdeveloper(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String created_by,@RequestParam Integer iduser);

    @PostMapping(value = "/deletedeveloper")
    public List<Object> deletedeveloper(@RequestParam Integer iddeveloper);

    @GetMapping(value = "/listdeveloper")
    public List<DeveloperBean> listDevelopers();

    @GetMapping(value = "/finddeveloper/{id}")
    public DeveloperBean finddeveloperbyid(@PathVariable Integer id);

    @PostMapping(value = "/createdeveloperskills")
    public List<Object> createdeveloperskills(@RequestParam Integer idDeveloper, @RequestParam Integer idskills, @RequestParam String created_by);

    @PostMapping(value = "/deletedeveloperskills")
    public List<Object> deletedeveloperskills(@RequestParam Integer iddeveloperskills);
    
    @GetMapping(value = "/listskillsbyIddeveloper")
    public List<SkillsBean> listSkillsbyIddeveloper(@RequestParam Integer iddeveloper);
       
    
    @GetMapping(value = "/listskills")
    public List<SkillsBean> listSkills();
    
    @PostMapping(value = "/udpatedeveloper")
    public List<Object> udpatedeveloper(@RequestParam Integer Iddeveloppeur, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String created_by);
    
    
     @GetMapping(value = "/listSkillsAutho_UnAutho_To_Developer")
     public List<SkillsInOutDeveloperBean> listSkillsAutho_UnAutho_To_Developer(@RequestParam Integer iddeveloper); 

     @PostMapping(value = "/deletedeveloperskillswithid")
     public List<Object> deletedeveloperskillswithid(@RequestParam Integer idDeveloper, @RequestParam Integer idskills); 
    
    
    //proxy projects
     
    @PostMapping(value = "/createprojects")
    public List<Object> createprojects(@RequestParam String name, @RequestParam String description, @RequestParam String created_by);
    @PostMapping(value = "/udpateprojects")
    public List<Object> udpateprojects(@RequestParam Integer Idprojects, @RequestParam String description, @RequestParam String updated_by);

    @PostMapping(value = "/deleteprojects")
    public List<Object> deleteprojects(@RequestParam Integer idproject);
    
    @GetMapping(value = "/listprojects")
    public List<ProjectsBean> listProjects();

    @GetMapping(value = "/findprojects/{id}")
    public ProjectsBean findprojectsbyid(@PathVariable Integer id);

    @PostMapping(value = "/createprojectmember")
    public List<Object> createprojectmember(@RequestParam Integer idProjects, @RequestParam Integer idDeveloper, @RequestParam String created_by); 

    @PostMapping(value = "/deleteprojectmember")
    public List<Object> deleteprojectmember(@RequestParam Integer idProjects, @RequestParam Integer idDeveloper); 

    @GetMapping(value = "/listDeveloperByIdProject")
    public List<DeveloperBean> listDeveloperByIdProject(@RequestParam Integer idprojects);
  
     
     @GetMapping(value = "/listDeveloperAutho_UnAutho_To_Projects")
     public List<DeveloperInOutProjectsBean> listDeveloperAutho_UnAutho_To_Projects(@RequestParam Integer idProjects); 
    
     //espace developer
     @GetMapping(value = "/listotherprojects")
     public List<ProjectsBean> listotherprojects(@RequestParam Integer iddeveloper);
    
     @GetMapping(value = "/listmyprojects")
     public List<ProjectsBean> listmyprojects(@RequestParam Integer iddeveloper);
    
     @GetMapping(value = "/finddeveloperbyiduser")
     public DeveloperBean finddeveloperbyiduser(@RequestParam Integer id);
     
     
    @PostMapping(value = "/createrequesttoparticipate")
    public List<Object> createrequesttoparticipate(@RequestParam Integer idProjects, @RequestParam Integer idDeveloper, @RequestParam String created_by);
    
    @PostMapping(value = "/validatedrequesttoparticipate")
    public List<Object> validatedrequesttoparticipate(@RequestParam Integer idrequesttoparticipate,@RequestParam String created_by);
    
    @PostMapping(value = "/rejectrequesttoparticipate")
    public List<Object> rejectrequesttoparticipate(@RequestParam Integer idrequesttoparticipate);
     
     @GetMapping(value = "/listrequesttoparticipate")
     public List<RequesttoparticipateBean> listrequesttoparticipate();
    
}
