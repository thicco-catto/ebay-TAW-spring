/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.DTO;


import es.taw.ebaytaw.entity.Listausuarios;
import es.taw.ebaytaw.entity.Users;

/**
 *
 * @author power
 */
public class UsuarioListaDTO {
    
        private Integer id;
        private Listausuarios listID;
        private Users userID;
        
    public UsuarioListaDTO() {
    }

    public UsuarioListaDTO(Integer id) {
        this.id = id;
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
    

    
}
