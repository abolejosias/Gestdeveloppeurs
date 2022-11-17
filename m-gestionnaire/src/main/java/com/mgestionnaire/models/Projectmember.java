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
@Table(name = "projectmember", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projectmember.findAll", query = "SELECT p FROM Projectmember p")
    , @NamedQuery(name = "Projectmember.findById", query = "SELECT p FROM Projectmember p WHERE p.id = :id")
    , @NamedQuery(name = "Projectmember.findByIdproject", query = "SELECT p FROM Projectmember p WHERE p.idproject = :idproject")
    , @NamedQuery(name = "Projectmember.findByIddeveloper", query = "SELECT p FROM Projectmember p WHERE p.iddeveloper = :iddeveloper")
    , @NamedQuery(name = "Projectmember.findByCreatedBy", query = "SELECT p FROM Projectmember p WHERE p.createdBy = :createdBy")
    , @NamedQuery(name = "Projectmember.findByUpdatedBy", query = "SELECT p FROM Projectmember p WHERE p.updatedBy = :updatedBy")
    , @NamedQuery(name = "Projectmember.findByCreatedAt", query = "SELECT p FROM Projectmember p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Projectmember.findByUpdatedAt", query = "SELECT p FROM Projectmember p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Projectmember.findByStrStatut", query = "SELECT p FROM Projectmember p WHERE p.strStatut = :strStatut")})
public class Projectmember implements Serializable {

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

    public Projectmember() {
    }

    public Projectmember(Integer id) {
        this.id = id;
    }

    public Projectmember(Integer id, int idproject, int iddeveloper) {
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
        if (!(object instanceof Projectmember)) {
            return false;
        }
        Projectmember other = (Projectmember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgestionnaire.models.Projectmember[ id=" + id + " ]";
    }
    
}
