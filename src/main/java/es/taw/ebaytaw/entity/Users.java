/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.entity;

import es.taw.ebaytaw.DTO.UsersDTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjura
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserID", query = "SELECT u FROM Users u WHERE u.userID = :userID")
    , @NamedQuery(name = "Users.findByRol", query = "SELECT u FROM Users u WHERE u.rol = :rol")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name")
    , @NamedQuery(name = "Users.findBySurname", query = "SELECT u FROM Users u WHERE u.surname = :surname")
    , @NamedQuery(name = "Users.findByGender", query = "SELECT u FROM Users u WHERE u.gender = :gender")
    , @NamedQuery(name = "Users.findByStreet", query = "SELECT u FROM Users u WHERE u.street = :street")
    , @NamedQuery(name = "Users.findByNumber", query = "SELECT u FROM Users u WHERE u.number = :number")
    , @NamedQuery(name = "Users.findByCity", query = "SELECT u FROM Users u WHERE u.city = :city")
    , @NamedQuery(name = "Users.findByRegion", query = "SELECT u FROM Users u WHERE u.region = :region")
    , @NamedQuery(name = "Users.findByPostalCode", query = "SELECT u FROM Users u WHERE u.postalcode = :postalCode")})
public class Users implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Mensaje> mensajeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Usuarioslista> usuarioslistaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userID")
    private Integer userID;
    @Basic(optional = false)
    @Column(name = "rol", nullable = false, length = 32)
    private String rol;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 128)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 128)
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    @Column(name = "name", nullable = true, length = 128) // insertable = false, updatable = false)
    private String name;
    @Column(name = "surname", nullable = true, length = 128)
    private String surname;
    @Column(name = "gender", nullable = true, length = 32)
    private String gender;
    @Column(name = "street", nullable = true, length = 128)
    private String street;
    @Column(name = "number")
    private Integer number;
    @Column(name = "city", nullable = true, length = 128)
    private String city;
    @Column(name = "region", nullable = true, length = 128)
    private String region;
    @Column(name = "postalcode")
    private Integer postalcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Categoriesuser> categoriesuserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Bids> bidsList;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    //private List<Studies> studiesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Products> productsList;

    public Users() {
    }

    public Users(Integer userID) {
        this.userID = userID;
    }

    public Users(Integer userID, String rol, String username, String password, String email) {
        this.userID = userID;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getPostalCode() {
        return postalcode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalcode = postalCode;
    }

    @XmlTransient
    public List<Categoriesuser> getCategoriesuserList() {
        return categoriesuserList;
    }

    public void setCategoriesuserList(List<Categoriesuser> categoriesuserList) {
        this.categoriesuserList = categoriesuserList;
    }

    @XmlTransient
    public List<Bids> getBidsList() {
        return bidsList;
    }

    public void setBidsList(List<Bids> bidsList) {
        this.bidsList = bidsList;
    }

    //@XmlTransient
    //public List<Studies> getStudiesList() {       return studiesList;    }

    //public void setStudiesList(List<Studies> studiesList) {        this.studiesList = studiesList;    }

    @XmlTransient
    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Users[ userID=" + userID + " ]";
    }
    
    // MIGUEL
    @Transient
    public UsersDTO toDTO(){
        UsersDTO dto = new UsersDTO();

        dto.setUserID(userID);
        dto.setRol(rol);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setEmail(email);
        dto.setName(name);
        dto.setSurname(surname);
        dto.setGender(gender);
        dto.setStreet(street);
        dto.setNumber(number);
        dto.setCity(city);
        dto.setRegion(region);
        dto.setPostalCode(postalcode);
        
        return dto;
    }

    @XmlTransient
    public List<Usuarioslista> getUsuarioslistaList() {
        return usuarioslistaList;
    }

    public void setUsuarioslistaList(List<Usuarioslista> usuarioslistaList) {
        this.usuarioslistaList = usuarioslistaList;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }
    
}
