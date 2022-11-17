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
@Table(name = "developer_skills", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeveloperSkills.findAll", query = "SELECT d FROM DeveloperSkills d")
    , @NamedQuery(name = "DeveloperSkills.findById", query = "SELECT d FROM DeveloperSkills d WHERE d.id = :id")
    , @NamedQuery(name = "DeveloperSkills.findByIddeveloper", query = "SELECT d FROM DeveloperSkills d WHERE d.iddeveloper = :iddeveloper")
    , @NamedQuery(name = "DeveloperSkills.findByIdskills", query = "SELECT d FROM DeveloperSkills d WHERE d.idskills = :idskills")
    , @NamedQuery(name = "DeveloperSkills.findByDescription", query = "SELECT d FROM DeveloperSkills d WHERE d.description = :description")
    , @NamedQuery(name = "DeveloperSkills.findByCreatedBy", query = "SELECT d FROM DeveloperSkills d WHERE d.createdBy = :createdBy")
    , @NamedQuery(name = "DeveloperSkills.findByUpdatedBy", query = "SELECT d FROM DeveloperSkills d WHERE d.updatedBy = :updatedBy")
    , @NamedQuery(name = "DeveloperSkills.findByCreatedAt", query = "SELECT d FROM DeveloperSkills d WHERE d.createdAt = :createdAt")
    , @NamedQuery(name = "DeveloperSkills.findByUpdatedAt", query = "SELECT d FROM DeveloperSkills d WHERE d.updatedAt = :updatedAt")
    , @NamedQuery(name = "DeveloperSkills.findByStrStatut", query = "SELECT d FROM DeveloperSkills d WHERE d.strStatut = :strStatut")})
public class DeveloperSkills implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "iddeveloper", nullable = false)
    private int iddeveloper;
    @Basic(optional = false)
    @Column(name = "idskills", nullable = false)
    private int idskills;
    @Column(name = "description", length = 500)
    private String description;
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

    public DeveloperSkills() {
    }

    public DeveloperSkills(Integer id) {
        this.id = id;
    }

    public DeveloperSkills(Integer id, int iddeveloper, int idskills) {
        this.id = id;
        this.iddeveloper = iddeveloper;
        this.idskills = idskills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIddeveloper() {
        return iddeveloper;
    }

    public void setIddeveloper(int iddeveloper) {
        this.iddeveloper = iddeveloper;
    }

    public int getIdskills() {
        return idskills;
    }

    public void setIdskills(int idskills) {
        this.idskills = idskills;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeveloperSkills)) {
            return false;
        }
        DeveloperSkills other = (DeveloperSkills) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgestionnaire.models.DeveloperSkills[ id=" + id + " ]";
    }
    
}
