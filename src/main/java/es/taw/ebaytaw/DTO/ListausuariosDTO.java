/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.DTO;


/**
 *
 * @author power
 */
public class ListausuariosDTO {
    private int listID;
    private String username;
    
    public ListausuariosDTO() {
    }

    public ListausuariosDTO(Integer listID) {
        this.listID = listID;
    }

    public ListausuariosDTO(Integer listID, String username) {
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
    
    public ListausuariosDTO toDTO(){
        ListausuariosDTO dto = new ListausuariosDTO();
        dto.setListID(listID);
        dto.setUsername(username);
        
        return dto;
        
    }

    /*
    @XmlTransient
    public List<Usuarioslista> getUsuarioslistaList() {
        return usuarioslistaList;
    }

    public void setUsuarioslistaList(List<Usuarioslista> usuarioslistaList) {
        this.usuarioslistaList = usuarioslistaList;
    }

    */
    
    

    
}
