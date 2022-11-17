/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.models;

/**
 *
 * @author hp i3
 */
public class DeveloperInOutProjects {
    
    private Integer id;
    private String first_name;
    private String last_name;
    private String skills;
    private String strStatut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStrStatut() {
        return strStatut;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    

    public void setStrStatut(String strStatut) {
        this.strStatut = strStatut;
    }
    
    
}
