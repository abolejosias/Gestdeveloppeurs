/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp i3
 */
@Entity
@Table(name = "requesttoparticipate", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requesttoparticipate.findAll", query = "SELECT r FROM Requesttoparticipate r")
    , @NamedQuery(name = "Requesttoparticipate.findById", query = "SELECT r FROM Requesttoparticipate r WHERE r.id = :id")
    , @NamedQuery(name = "Requesttoparticipate.findByIdproject", query = "SELECT r FROM Requesttoparticipate r WHERE r.idproject = :idproject")
    , @NamedQuery(name = "Requesttoparticipate.findByIddeveloper", query = "SELECT r FROM Requesttoparticipate r WHERE r.iddeveloper = :iddeveloper")
    , @NamedQuery(name = "Requesttoparticipate.findByStrMessage", query = "SELECT r FROM Requesttoparticipate r WHERE r.strMessage = :strMessage")
    , @NamedQuery(name = "Requesttoparticipate.findByCreatedAt", query = "SELECT r FROM Requesttoparticipate r WHERE r.createdAt = :createdAt")
    , @NamedQuery(name = "Requesttoparticipate.findByUpdatedAt", query = "SELECT r FROM Requesttoparticipate r WHERE r.updatedAt = :updatedAt")
    , @NamedQuery(name = "Requesttoparticipate.findByStrStatut", query = "SELECT r FROM Requesttoparticipate r WHERE r.strStatut = :strStatut")})
public class Requesttoparticipate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idproject", nullable = false)
    private int idproject;
    @Basic(optional = false)
    @Column(name = "iddeveloper", nullable = false)
    private int iddeveloper;
    @Column(name = "str_message", length = 500)
    private String strMessage;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "str_statut", length = 50)
    private String strStatut;

    public Requesttoparticipate() {
    }

    public Requesttoparticipate(Integer id) {
        this.id = id;
    }

    public Requesttoparticipate(Integer id, int idproject, int iddeveloper) {
        this.id = id;
        this.idproject = idproject;
        this.iddeveloper = iddeveloper;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requesttoparticipate)) {
            return false;
        }
        Requesttoparticipate other = (Requesttoparticipate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgestionnaire.models.Requesttoparticipate[ id=" + id + " ]";
    }
    
}
