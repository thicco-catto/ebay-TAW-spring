/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.service;

import es.taw.ebaytaw.DTO.CategoriesDTO;
import es.taw.ebaytaw.entity.Categories;
import es.taw.ebaytaw.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cristobal
 */
@Service
public class CategoriesService {

    CategoriesRepository cf;

    //Cristobal
    public List<CategoriesDTO> listaEntityADTO(List<Categories> categorias) {
        return categorias.stream().map(x -> x.toDTO()).collect(Collectors.toList());
    }

    //Cristobal
    public List<CategoriesDTO> listarCategorias(String filtroTitulo) {
        List<Categories> categorias = null;
        if (filtroTitulo == null || filtroTitulo.isEmpty()) {
            categorias = this.cf.findAll();
        } else {
            categorias = this.cf.getAllByName(filtroTitulo);
        }

        return this.listaEntityADTO(categorias);
    }
    
    //Cristobal
    public CategoriesDTO getCategoria(Integer categoriaId){
        Categories category = this.cf.findById(categoriaId).orElse(null);
        return category.toDTO();
    }
    
    //Cristobal
    public void borrarCategoria(Integer categoriaId){
        Categories category = this.cf.findById(categoriaId).orElse(null);
        this.cf.delete(category);
    }
    
    //Cristobal
    public void crearCategoria(String nombre){
        Categories category = new Categories();
        category.setName(nombre);
        this.cf.save(category);
    }
    
    //Cristobal
    public void editarCategoria(Integer categoriaId, String nombre){
        Categories category = this.cf.findById(categoriaId).orElse(null);
        category.setName(nombre);
        this.cf.save(category);
    }

    // Miguel
    public List<CategoriesDTO> findAll(){
        List<Categories> categorias = this.cf.findAll();
        return this.listaEntityADTO(categorias);
    }

    public void setCf(CategoriesRepository categoriesRepository) {
        this.cf = categoriesRepository;
    }
}
