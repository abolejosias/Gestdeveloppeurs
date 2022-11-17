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
import javax.persistence.Lob;
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
@Table(name = "projects", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p")
    , @NamedQuery(name = "Projects.findById", query = "SELECT p FROM Projects p WHERE p.id = :id")
    , @NamedQuery(name = "Projects.findByName", query = "SELECT p FROM Projects p WHERE p.name = :name")
    , @NamedQuery(name = "Projects.findByCreatedBy", query = "SELECT p FROM Projects p WHERE p.createdBy = :createdBy")
    , @NamedQuery(name = "Projects.findByUpdatedBy", query = "SELECT p FROM Projects p WHERE p.updatedBy = :updatedBy")
    , @NamedQuery(name = "Projects.findByCreatedAt", query = "SELECT p FROM Projects p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Projects.findByUpdatedAt", query = "SELECT p FROM Projects p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Projects.findByStrStatut", query = "SELECT p FROM Projects p WHERE p.strStatut = :strStatut")})
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 75)
    private String name;
    @Lob
    @Column(name = "description", length = 65535)
    private String description;
    @Column(name = "devnumber")
    private Integer devnumber;
    @Column(name = "created_by", length = 40)
    private String createdBy;
    @Column(name = "updated_by", length = 40)
    private String updatedBy;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "str_statut", length = 20)
    private String strStatut;
    
    @Column(name = "etatdemande")
    private Boolean etatdemande;

    public Projects() {
    }

    public Projects(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDevnumber() {
        return devnumber;
    }

    public void setDevnumber(Integer devnumber) {
        this.devnumber = devnumber;
    }

    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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

    public Boolean getEtatdemande() {
        return etatdemande;
    }

    public void setEtatdemande(Boolean etatdemande) {
        this.etatdemande = etatdemande;
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
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgestionnaire.models.Projects[ id=" + id + " ]";
    }
    
}
