/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.service;

import es.taw.ebaytaw.DTO.ProductsDTO;
import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.entity.Categories;
import es.taw.ebaytaw.entity.Products;
import es.taw.ebaytaw.entity.Users;
import es.taw.ebaytaw.repository.CategoriesRepository;
import es.taw.ebaytaw.repository.ProductsRepository;
import es.taw.ebaytaw.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mjura
 */
@Service
public class ProductService {
    
    ProductsRepository pf;
    CategoriesRepository cf;
    UsersRepository uf;
    
    public List<ProductsDTO> listaEntityADTO (List<Products> lista){
        List<ProductsDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Products producto : lista) {
                listaDTO.add(producto.toDTO());
            }
        }
        return listaDTO;
    }

    public List<ProductsDTO> listarProductos(String filtroTitulo) {
        List<Products> productos = null;
        if (filtroTitulo == null || filtroTitulo.isEmpty()) {
            productos = this.pf.findAll();
        } else {
            productos = this.pf.getAllByTitle(filtroTitulo);
        }


        return this.listaEntityADTO(productos);
    }


    // Miguel
    public List<ProductsDTO> listarProductos (String filtroTitulo, UsersDTO vendedor){
        List<Products> productos = null;
        Users user = this.uf.findById(vendedor.getUserID()).orElse(null);
        if (filtroTitulo == null || filtroTitulo.isEmpty()) {
            productos = this.pf.getAllByUserID(user);
        } else {
            productos = this.pf.getAllByTitleAndUserID(filtroTitulo, user);
        }

        return this.listaEntityADTO(productos);
    }

    public void borrarProducto(Integer id) {
        Products producto = this.pf.findById(id).orElse(null);
        this.pf.delete(producto);
    }

    public ProductsDTO buscarProducto(Integer id) {
        Products producto = this.pf.findById(id).orElse(null);
        return producto.toDTO();
    }
    
    
    // Borrar??
    public void editarProducto(Integer id, String titulo, String descripcion, BigDecimal precioInicial, String foto, Date fechaInicio, Date fechaFin, Boolean vendido){
        Products producto = this.pf.findById(id).orElse(null);

        producto.setTitle(titulo);
        producto.setDescription(descripcion);
        producto.setInitialPrice(precioInicial);
        producto.setPhoto(foto);
        producto.setStartDate(fechaInicio);
        producto.setFinishDate(fechaFin);
        producto.setIsSold(vendido);
        this.pf.save(producto);
    }

    //Cristobal
    public List<ProductsDTO> listarProductos(ProductsDTO producto){
        return listarProductos(producto.getTitle(), producto.getUserIDint(), producto.getCategoryIDint(),
                producto.getInitialPrice(), producto.getStartDate(), producto.getFinishDate(), producto.getIsSold());
    }

    //Cristobal
    public List<ProductsDTO> listarProductos(String title, Integer userId, Integer categoryId, BigDecimal initialPrice, Date startDate, Date finishDate, Boolean isSold) {
        List<Products> productos;

        if ((title == null || title.isEmpty()) && userId == null && categoryId == null && initialPrice == null && startDate == null && finishDate == null && isSold == null) {
            productos = this.pf.findAll();
        } else {
            productos = this.pf.findAll(title, userId, categoryId, initialPrice, startDate, finishDate, isSold);
        }

        return this.listaEntityADTO(productos);
    }

    //Cristobal
    public List<ProductsDTO> listarProductos(){
        List<Products> products = this.pf.findAll();
        return listaEntityADTO(products);
    }
    
    // Miguel
    public void editarProducto(Integer id, String titulo, String descripcion, String categoria, BigDecimal pInicial, String linkFoto, Date fInicio, Date fFin, Boolean v){
        
        //Busco el producto
        Products producto = this.pf.findById(id).orElse(null);
        Categories cat = this.cf.getByName(categoria);
        
        producto.setTitle(titulo);
        producto.setDescription(descripcion);
        producto.setCategoryID(cat);
        producto.setInitialPrice(pInicial);
        producto.setPhoto(linkFoto);
        producto.setStartDate(fInicio);
        producto.setFinishDate(fFin);
        producto.setIsSold(v);
        
        this.pf.save(producto);
    }
    
    //Cristobal
    public void editarProducto(Integer productId, String titulo, String descripcion, String linkFoto, Integer categoriaId, BigDecimal pInicial, Date fInicio, Date fFin, Boolean v){
        
        //Busco el producto
        Products producto = this.pf.findById(productId).orElse(null);
        Categories cat = this.cf.findById(categoriaId).orElse(null);
        
        producto.setTitle(titulo);
        producto.setDescription(descripcion);
        producto.setCategoryID(cat);
        producto.setInitialPrice(pInicial);
        producto.setPhoto(linkFoto);
//        producto.setStartDate(fInicio);
//        producto.setFinishDate(fFin);
        producto.setIsSold(v);
        
        this.pf.save(producto);
    }
    
    //Miguel
    public void crearProducto(String id, String titulo, String descripcion, String categoria, BigDecimal precio, String foto, Date finicio, Date ffin){
        Products producto = new Products();
        Categories cat = this.cf.getByName(categoria);
        Users usuario = this.uf.findById(Integer.parseInt(id)).orElse(null);
        List<Products> productosUsuario = this.pf.getAllByUserID(usuario);
        
        producto.setUserID(usuario);
        producto.setTitle(titulo);
        producto.setDescription(descripcion);
        producto.setCategoryID(cat);
        producto.setInitialPrice(precio);
        producto.setPhoto(foto);
        producto.setStartDate(finicio);
        producto.setFinishDate(ffin);
        producto.setIsSold(Boolean.FALSE);
        
        this.pf.save(producto);
        productosUsuario.add(producto);
        usuario.setProductsList(productosUsuario);
        this.uf.save(usuario);
    }
    
    //Miguel
    public void setVendido (Integer productoId){
        Products producto = this.pf.findById(productoId).orElse(null);
        producto.setIsSold(Boolean.TRUE);
        this.pf.save(producto);
    }

    //Cristobal
    public void editarProducto(ProductsDTO product){
        editarProducto(product.getProductID(), product.getTitle(), product.getDescription(), product.getPhoto(),
                product.getCategoryIDint(), product.getInitialPrice(), product.getStartDate(), product.getFinishDate(),
                product.getIsSold());
    }

    public void setPf(ProductsRepository productsRepository) {
        this.pf = productsRepository;
    }

    public void setCf(CategoriesRepository categoriesRepository) { this.cf = categoriesRepository; }

    public void setUf(UsersRepository usersRepository) { this.uf = usersRepository; }
}
