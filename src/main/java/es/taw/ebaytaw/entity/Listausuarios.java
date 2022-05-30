/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import DTO.ListausuarioDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author power
 */
@Entity
@Table(name = "listausuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listausuarios.findAll", query = "SELECT l FROM Listausuarios l")
    , @NamedQuery(name = "Listausuarios.findByListID", query = "SELECT l FROM Listausuarios l WHERE l.listID = :listID")
    , @NamedQuery(name = "Listausuarios.findByUsername", query = "SELECT l FROM Listausuarios l WHERE l.username = :username")})
public class Listausuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "listID")
    private Integer listID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "username", nullable = false, size = 128)
    private String username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listID")
    private List<Usuarioslista> usuarioslistaList;

    public Listausuarios() {
    }

    public Listausuarios(Integer listID) {
        this.listID = listID;
    }

    public Listausuarios(Integer listID, String username) {
        this.listID = listID;
        this.username = username;
    }

    public Integer getListID() {
        return listID;
    }

    public void setListID(Integer listID) {
        this.listID = listID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public List<Usuarioslista> getUsuarioslistaList() {
        return usuarioslistaList;
    }

    public void setUsuarioslistaList(List<Usuarioslista> usuarioslistaList) {
        this.usuarioslistaList = usuarioslistaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listID != null ? listID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listausuarios)) {
            return false;
        }
        Listausuarios other = (Listausuarios) object;
        if ((this.listID == null && other.listID != null) || (this.listID != null && !this.listID.equals(other.listID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Listausuarios[ listID=" + listID + " ]";
    }
    
    
        public ListausuarioDTO toDTO(){
        ListausuarioDTO DTO = new ListausuarioDTO();
        DTO.setListID(this.listID);
        DTO.setUsername(this.username);
        
        return DTO;
    }
    
}
