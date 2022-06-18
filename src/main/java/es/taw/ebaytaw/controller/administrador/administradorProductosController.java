package es.taw.ebaytaw.controller.administrador;

import es.taw.ebaytaw.DTO.CategoriesDTO;
import es.taw.ebaytaw.DTO.ProductsDTO;
import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.repository.CategoriesRepository;
import es.taw.ebaytaw.repository.ProductsRepository;
import es.taw.ebaytaw.repository.UsersRepository;
import es.taw.ebaytaw.service.CategoriesService;
import es.taw.ebaytaw.service.ProductsService;
import es.taw.ebaytaw.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador/productos")
public class administradorProductosController {
    protected UsersService us;
    protected ProductsService ps;
    protected CategoriesService cs;

    @Autowired
    public void setUsersService(UsersService usersService, UsersRepository usersRepository) {
        this.us = usersService;
        this.us.setUf(usersRepository);
    }

    @Autowired
    public void setProductsService(ProductsService productsService, ProductsRepository productsRepository){
    //public void setProductsService(ProductService productsService, ProductsRepository productsRepository,
    //                               UsersRepository usersRepository, CategoriesRepository categoriesRepository){
        this.ps = productsService;
        //this.ps.setPf(productsRepository);
        //this.ps.setCf(categoriesRepository);
        //this.ps.setUf(usersRepository);
    }

    @Autowired
    public void setCategoriesService(CategoriesService categoriesService, CategoriesRepository categoriesRepository){
        this.cs = categoriesService;
        this.cs.setCf(categoriesRepository);
    }

    @GetMapping("")
    public String showProducts(Model model){
        setModelParameters(model);

        List<ProductsDTO> products = this.ps.listarProductos();
        model.addAttribute("products", products);

        ProductsDTO productFilter = new ProductsDTO();
        model.addAttribute("productFilter", productFilter);

        return "/administrador/administrador_productos";
    }

    @PostMapping("/filtrar")
    public String deleteProduct(Model model, @ModelAttribute("productFilter") ProductsDTO productFilter){
        System.out.println(productFilter);
        setModelParameters(model);

        List<ProductsDTO> products = this.ps.listarProductos(productFilter);
        model.addAttribute("products", products);

        model.addAttribute("productFilter", productFilter);

        return "/administrador/administrador_productos";
    }

    private void setModelParameters(Model model) {
        List<CategoriesDTO> categories = this.cs.findAll();
        List<UsersDTO> users = this.us.listarUsuarios();

        model.addAttribute("categories", categories);
        model.addAttribute("users", users);
    }

    @GetMapping("/eliminar/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        this.ps.borrarProducto(id);

        return "redirect:/administrador/productos";
    }

    @GetMapping("/editar/{id}")
    public String editProduct(Model model, @PathVariable("id") Integer id){
        ProductsDTO product = this.ps.buscarProducto(id);
        model.addAttribute("product", product);

        List<CategoriesDTO> categories = this.cs.findAll();
        model.addAttribute("categories", categories);

        return "/administrador/administrador_editar_producto";
    }

    @PostMapping("/guardarEditado")
    public String editProduct(@ModelAttribute("product") ProductsDTO product){
        this.ps.editarProducto(product);

        return "redirect:/administrador/productos";
    }
}
