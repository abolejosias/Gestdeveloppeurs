/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmodules.web.controller;

import com.mmodules.models.Module;
import com.mmodules.repository.ModuleRepository;
import com.mmodules.toolkits.commonparameter;
import com.mmodules.web.exception.MessageNotFoundException;
import com.mmodules.web.exception.ModuleNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp i3
 */
@RestController
public class ModuleController {

    @Autowired
    ModuleRepository OmoduleRepository;

    @GetMapping(value = "/module")
    public List<Module> listedesmodules() {
        List<Module> lstmodule = OmoduleRepository.findAllmodule(commonparameter.statut_enable);
        if (lstmodule.isEmpty()) throw new ModuleNotFoundException(commonparameter.LIST_EMPHTY);
        return lstmodule;
    }

    @GetMapping(value = "/module/{id}")
    public Module recupererunmodule(@PathVariable int id) {
        Optional<Module> optionalModule = OmoduleRepository.findById(id);
        if (!optionalModule.isPresent()) throw new ModuleNotFoundException(commonparameter.LIST_EMPHTY); 
        return optionalModule.get();
    }
    
    @GetMapping(value = "/findmodule/{id}")
    public Module findmodule(@PathVariable int id) {
        Optional<Module> optionalModule = OmoduleRepository.findById(id);
        if (!optionalModule.isPresent()) throw new ModuleNotFoundException(commonparameter.LIST_EMPHTY); 
        return optionalModule.get();
    }

    @PostMapping(value = "/creermodule")
    public List<Object> ajoutermodule(@RequestBody Module module) {
        Module Omodule = OmoduleRepository.findbyStrValue(module.getStrValue());
        if (Omodule != null) {
            return MessageNotFoundException.getMessageException(commonparameter.PROCESS_FAILED, commonparameter.EXIST_DATA);
        } else {
            Module module1 = OmoduleRepository.save(module);
            if (module1 == null) {
                return MessageNotFoundException.getMessageException(commonparameter.PROCESS_FAILED, commonparameter.ECHEC_CREATION);
            } else {
                return MessageNotFoundException.getMessageException(commonparameter.PROCESS_SUCCESS, commonparameter.SUCCES_CREATION);
            }
        }
    }

    @PutMapping(value = "/updatemodule")
    public List<Object> updatemodule(@RequestBody Module module) {
        OmoduleRepository.save(module);
        return MessageNotFoundException.getMessageException(commonparameter.PROCESS_SUCCESS, commonparameter.SUCCES_UPDATE);
    }

    
    @PostMapping(value="/deletemodule/{id}")
    public List<Object> deletemodule(@PathVariable Integer id){
        OmoduleRepository.deleteById(id);
        return MessageNotFoundException.getMessageException(commonparameter.PROCESS_SUCCESS, commonparameter.SUCCES_DELETE);
    }
}
