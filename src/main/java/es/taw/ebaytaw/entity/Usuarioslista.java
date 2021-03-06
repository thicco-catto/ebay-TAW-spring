/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.entity;

import org.apache.catalina.User;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author power
 */
@Entity
@Table(name = "usuarioslista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarioslista.findAll", query = "SELECT u FROM Usuarioslista u")
    , @NamedQuery(name = "Usuarioslista.findById", query = "SELECT u FROM Usuarioslista u WHERE u.id = :id")})
public class Usuarioslista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "listID", referencedColumnName = "listID")
    @ManyToOne(optional = false)
    private Listausuarios listID;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users userID;

    public Usuarioslista() {
    }

    public Usuarioslista(Integer id) {
        this.id = id;
    }

    public Usuarioslista(Users users, Listausuarios list) {
        this.userID = users;
        this.listID = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Listausuarios getListID() {
        return listID;
    }

    public void setListID(Listausuarios listID) {
        this.listID = listID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
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
        if (!(object instanceof Usuarioslista)) {
            return false;
        }
        Usuarioslista other = (Usuarioslista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Usuarioslista[ id=" + id + " ]";
    }
    
}
