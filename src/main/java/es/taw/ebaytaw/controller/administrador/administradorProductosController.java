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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        this.ps = productsService;
        this.ps.setPf(productsRepository);
    }

    @Autowired
    public void setCategoriesService(CategoriesService categoriesService, CategoriesRepository categoriesRepository){
        this.cs = categoriesService;
        this.cs.setCf(categoriesRepository);
    }

    @GetMapping("")
    public String showProducts(Model model){
        List<ProductsDTO> products = this.ps.listarProductos();
        List<CategoriesDTO> categories = this.cs.findAll();
        List<UsersDTO> users = this.us.listarUsuarios();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("users", users);

        ProductsDTO productFilter = new ProductsDTO();
        model.addAttribute("productFilter", productFilter);

        return "/administrador/administrador_productos";
    }
}
