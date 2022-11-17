/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.proxies;

import com.frontui.beans.ModuleBean;
import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author hp i3
 */
@FeignClient(name = "m-modules")
@RibbonClient(name = "m-modules")
public interface MmoduleProxy {

    @GetMapping(value = "/module")
    public List<ModuleBean> listedesmodules();

    @GetMapping(value = "/module/{id}")
    public ModuleBean recupererunmodule(@PathVariable int id);
    
    @GetMapping(value = "/findmodule")
    public ModuleBean findmodule(@PathVariable int id);

    @PostMapping(value = "/creermodule")
    public List<Object> ajoutermodule(@RequestBody ModuleBean module);

    @PutMapping(value = "/updatemodule")
    public List<Object> updatemodule(@RequestBody ModuleBean module);
    
     @PostMapping(value="/deletemodule/{id}")
    public List<Object> deletemodule(@PathVariable Integer id);
      

}
