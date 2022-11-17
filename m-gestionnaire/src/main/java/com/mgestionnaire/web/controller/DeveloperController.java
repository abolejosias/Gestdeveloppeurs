/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.web.controller;

import com.mgestionnaire.models.Developer;
import com.mgestionnaire.models.Skills;
import com.mgestionnaire.models.SkillsInOutDeveloper;
import com.mgestionnaire.service.DeveloperService;
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
public class DeveloperController {

    @Autowired
    DeveloperService developerService;

    @PostMapping(value = "/createdeveloper")
    public List<Object> createdeveloper(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String created_by,@RequestParam Integer iduser) {
        System.err.println("first_name  ********************************************" + first_name);
        developerService.CreateDeveloper(first_name, last_name, created_by,iduser);
        return MessageNotFoundException.getMessageException(developerService.getMessage(), developerService.getDetailmessage());
    }
    
    @PostMapping(value = "/udpatedeveloper")
    public List<Object> udpatedeveloper(@RequestParam Integer Iddeveloppeur, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String created_by) {
        System.err.println("first_name  ********************************************" + first_name);
        developerService.UpdateDeveloper(Iddeveloppeur,first_name, last_name, created_by);
        return MessageNotFoundException.getMessageException(developerService.getMessage(), developerService.getDetailmessage());
    }
    
    

    @PostMapping(value = "/deletedeveloper")
    public List<Object> deletedeveloper(@RequestParam Integer iddeveloper) {
        developerService.DeleteDeveloper(iddeveloper);
        return MessageNotFoundException.getMessageException(developerService.getMessage(), developerService.getDetailmessage());
    }

    @GetMapping(value = "/listdeveloper")
    public List<Developer> listDevelopers() {
        List<Developer> listdeveloper = developerService.getListDevelopers(commonparameter.statut_enable);
        return listdeveloper;
    }

    @GetMapping(value = "/finddeveloper/{id}")
    public Developer finddeveloperbyid(@PathVariable Integer id) {
        return developerService.findByIdDeveloper(id);
    }

    @PostMapping(value = "/createdeveloperskills")
    public List<Object> createdeveloperskills(@RequestParam Integer idDeveloper, @RequestParam Integer idskills, @RequestParam String created_by) {
        System.err.println("idskills  ********************************************" + idskills);
        developerService.CreateDeveloperSkills(idDeveloper, idskills, created_by);
        return MessageNotFoundException.getMessageException(developerService.getMessage(), developerService.getDetailmessage());
    }

    @PostMapping(value = "/deletedeveloperskills")
    public List<Object> deletedeveloperskills(@RequestParam Integer iddeveloperskills) {
        developerService.DeleteDeveloperSkills(iddeveloperskills);
        return MessageNotFoundException.getMessageException(developerService.getMessage(), developerService.getDetailmessage());
    }
    
      @PostMapping(value = "/deletedeveloperskillswithid")
    public List<Object> deletedeveloperskillswithid(@RequestParam Integer idDeveloper, @RequestParam Integer idskills) {
        developerService.DeleteDeveloperSkills(idDeveloper, idskills);
        return MessageNotFoundException.getMessageException(developerService.getMessage(), developerService.getDetailmessage());
    }

    @GetMapping(value = "/listskillsbyIddeveloper")
    public List<Skills> listSkillsbyIddeveloper(@RequestParam Integer iddeveloper) {
        List<Skills> listSkills = developerService.getListSkillsByIdDeveloper(iddeveloper);
        return listSkills;
    }
    
     @GetMapping(value = "/listskills")
     public List<Skills> listSkills() {
        List<Skills> listSkills = developerService.getListSkills();
        return listSkills;
    }
     
     @GetMapping(value = "/listSkillsAutho_UnAutho_To_Developer")
     public List<SkillsInOutDeveloper> listSkillsAutho_UnAutho_To_Developer(@RequestParam Integer iddeveloper) {
        List<SkillsInOutDeveloper> listSkillsInOutDeveloper = developerService.getAllSkillsAutho_UnAutho_To_Developer(String.valueOf(iddeveloper));
        return listSkillsInOutDeveloper;
    }
     
    
    
     @GetMapping(value = "/finddeveloperbyiduser")
    public Developer finddeveloperbyiduser(@RequestParam Integer id) {
        return developerService.findByIdDeveloperByIduser(id);
    }

     
    
}
