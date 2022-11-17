/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.proxies;

import com.mmenu.models.Module;
import java.util.List;
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
@FeignClient(name = "m-modules",url = "localhost:9009")
public interface MmoduleProxy {

    @GetMapping(value = "/module")
    public List<Module> listedesmodules();

    @GetMapping(value = "/module/{id}")
    public Module recupererunmodule(@PathVariable int id);

    @GetMapping(value = "/findmodule/{id}")
    public Module findmodule(@PathVariable int id);

    @PostMapping(value = "/creermodule")
    public List<Object> ajoutermodule(@RequestBody Module module);

    @PutMapping(value = "/updatemodule")
    public List<Object> updatemodule(@RequestBody Module module);

    @PostMapping(value = "/deletemodule/{id}")
    public List<Object> deletemodule(@PathVariable Integer id);
}
