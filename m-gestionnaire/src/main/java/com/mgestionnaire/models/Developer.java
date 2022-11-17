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
@Table(name = "developer", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Developer.findAll", query = "SELECT d FROM Developer d")
    , @NamedQuery(name = "Developer.findById", query = "SELECT d FROM Developer d WHERE d.id = :id")
    , @NamedQuery(name = "Developer.findByFirstName", query = "SELECT d FROM Developer d WHERE d.firstName = :firstName")
    , @NamedQuery(name = "Developer.findByLastName", query = "SELECT d FROM Developer d WHERE d.lastName = :lastName")
    , @NamedQuery(name = "Developer.findByCreatedBy", query = "SELECT d FROM Developer d WHERE d.createdBy = :createdBy")
    , @NamedQuery(name = "Developer.findByUpdatedBy", query = "SELECT d FROM Developer d WHERE d.updatedBy = :updatedBy")
    , @NamedQuery(name = "Developer.findByCreatedAt", query = "SELECT d FROM Developer d WHERE d.createdAt = :createdAt")
    , @NamedQuery(name = "Developer.findByUpdatedAt", query = "SELECT d FROM Developer d WHERE d.updatedAt = :updatedAt")
    , @NamedQuery(name = "Developer.findByStrStatut", query = "SELECT d FROM Developer d WHERE d.strStatut = :strStatut")})
public class Developer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "first_name", length = 75)
    private String firstName;
    @Column(name = "last_name", length = 200)
    private String lastName;
    @Column(name = "iduser")
    private Integer iduser;
    
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

    public Developer() {
    }

    public Developer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
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
        if (!(object instanceof Developer)) {
            return false;
        }
        Developer other = (Developer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgestionnaire.models.Developer[ id=" + id + " ]";
    }
    
}
