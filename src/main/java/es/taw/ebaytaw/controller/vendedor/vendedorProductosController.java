package es.taw.ebaytaw.controller.vendedor;

import es.taw.ebaytaw.DTO.CategoriesDTO;
import es.taw.ebaytaw.DTO.ProductsDTO;
import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.repository.CategoriesRepository;
import es.taw.ebaytaw.repository.ProductsRepository;
import es.taw.ebaytaw.repository.UsersRepository;
import es.taw.ebaytaw.service.CategoriesService;
import es.taw.ebaytaw.service.ProductService;
import es.taw.ebaytaw.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vendedor/productos")
public class vendedorProductosController {

    protected ProductService ps;
    protected CategoriesService cs;

    @Autowired
    public void setProductService(ProductService productService, ProductsRepository productsRepository,
                                  UsersRepository usersRepository, CategoriesRepository categoriesRepository){
        this.ps = productService;
        this.ps.setPf(productsRepository);
        this.ps.setUf(usersRepository);
        this.ps.setCf(categoriesRepository);
    }

    @Autowired
    public void setCategoriesService(CategoriesService categoriesService, CategoriesRepository categoriesRepository){
        this.cs = categoriesService;
        this.cs.setCf(categoriesRepository);
    }


    @GetMapping("")
    public String showProducts(Model model, HttpSession session){
        String filtro = "";
        UsersDTO vendedor = (UsersDTO)session.getAttribute("usuario");
        List<ProductsDTO> products = ps.listarProductos(filtro, vendedor);
        model.addAttribute("productos", products);
        List<CategoriesDTO> categorias = cs.findAll();
        model.addAttribute("categorias", categorias);

        return "/vendedor/vendedor_productos";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        this.ps.borrarProducto(id);
        return "redirect:/vendedor/productos";
    }

    @GetMapping("/toadjudicar/{id}")
    public String toAdjudicar(Model model, @PathVariable("id") Integer id){
        ProductsDTO product = this.ps.buscarProducto(id);
        model.addAttribute("product", product);

        return "vendedor/vendedor_adjudicar";
    }

    @GetMapping("/adjudicar/{id}")
    public String adjudicar(@PathVariable("id") Integer id){
        this.ps.setVendido(id);
        return "redirect:/vendedor/productos";
    }

    @GetMapping("/toeditar/{id}")
    public String toEditProduct(Model model, @PathVariable("id") Integer id){
        ProductsDTO product = this.ps.buscarProducto(id);
        model.addAttribute("product", product);

        List<CategoriesDTO> categories = this.cs.findAll();
        model.addAttribute("categories", categories);

        return "/vendedor/vendedor_editar_producto";
    }

    @PostMapping("/editar")
    public String editProduct(@ModelAttribute("product") ProductsDTO product){
        this.ps.editarProducto(product);

        return "redirect:/vendedor/productos";
    }

    @GetMapping("/tocrearproducto")
    public String toCreateProduct(Model model){
        ProductsDTO product = new ProductsDTO();
        model.addAttribute("product", product);

        List<CategoriesDTO> categories = this.cs.findAll();
        model.addAttribute("categories", categories);

        return "/vendedor/vendedor_crear_producto";
    }


    @PostMapping("/crearproducto")
    public String createProduct(String titulo, String descripcion, String categoria,
                                String precioInicial, String foto, String fechaInicio,
                                String fechaFin, HttpSession session){

        UsersDTO vendedor = (UsersDTO)session.getAttribute("usuario");
        Integer id = vendedor.getUserID();
        BigDecimal pInicial = new BigDecimal(precioInicial);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fInicio = null;
        try {
            fInicio = format.parse(fechaInicio);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date fFin = null;
        try {
            fFin = format.parse(fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(fFin);

        this.ps.crearProducto(id, titulo, descripcion, categoria, pInicial, foto, fInicio, fFin);

        return "redirect:/vendedor/productos";
    }

    @GetMapping("/salir")
    public String doExit (HttpSession session) {
        session.invalidate();
        return "login";
    }

}
