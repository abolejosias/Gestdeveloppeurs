/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.beans;

import java.util.Date;

/**
 *
 * @author hp i3
 */
public class RequesttoparticipateBean {

    private Integer id;
    private int idproject;
    private int iddeveloper;
    private String strMessage;
    private Date createdAt;
    private Date updatedAt;
    private String strStatut;

    public RequesttoparticipateBean() {
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdproject() {
        return idproject;
    }

    public void setIdproject(int idproject) {
        this.idproject = idproject;
    }

    public int getIddeveloper() {
        return iddeveloper;
    }

    public void setIddeveloper(int iddeveloper) {
        this.iddeveloper = iddeveloper;
    }

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStrStatut() {
        return strStatut;
    }

    public void setStrStatut(String strStatut) {
        this.strStatut = strStatut;
    }

   

}
