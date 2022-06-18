package es.taw.ebaytaw.controller.comprador;

import es.taw.ebaytaw.DTO.BidsDTO;
import es.taw.ebaytaw.DTO.CategoriesDTO;
import es.taw.ebaytaw.DTO.ProductsDTO;
import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.service.BidsService;
import es.taw.ebaytaw.service.CategoriesService;
import es.taw.ebaytaw.service.ProductsService;
import es.taw.ebaytaw.vo.ProductsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comprador/productos")
public class CompradorProductosController {
    private ProductsService productsService;
    private CategoriesService categoriesService;
    private BidsService bidsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setCategoriesService(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @Autowired
    public void setBidsService(BidsService bidsService) {
        this.bidsService = bidsService;
    }

    public Boolean comprobarSesion(HttpSession sesion) {
        return sesion.getAttribute("usuario") == null;
    }

    @GetMapping("/")
    public String doInicio(HttpSession sesion) {
        return comprobarSesion(sesion) ? "redirect:/salir" : "redirect:/comprador/productos/en-venta";
    }

    @GetMapping("/en-venta")
    public String doListarProductosEnVenta(Model modelo, HttpSession sesion) {
        ProductsFilter filtroProductos = new ProductsFilter();

        return this.doFiltrarProductosEnVenta(filtroProductos, modelo, sesion);
    }

    @GetMapping("/pujados")
    public String doListarProductosPujados(Model modelo, HttpSession sesion) {
        ProductsFilter filtroProductos = new ProductsFilter();

        return this.doFiltrarProductosPujados(filtroProductos, modelo, sesion);
    }

    @GetMapping("/subir-puja")
    public String doProductoSubirPuja(@ModelAttribute("puja") BidsDTO puja, HttpSession sesion) {
        String goTo = "redirect:/salir";

        if (!comprobarSesion(sesion)) {
            this.bidsService.subirPuja(puja);

            goTo = "redirect:/comprador/productos/pujados";
        }


        return goTo;
    }

    @GetMapping("/retirar-puja")
    public String doProductoRetirarPuja(@ModelAttribute("puja") BidsDTO puja, HttpSession sesion) {
        String goTo = "redirect:/salir";

        if (!comprobarSesion(sesion)) {
            this.bidsService.retirarPuja(puja);

            goTo = "redirect:/comprador/productos/en-venta";
        }

        return goTo;
    }

    @PostMapping("/en-venta/filtrar")
    public String doFiltrarProductosEnVenta(@ModelAttribute("filtroProductos") ProductsFilter filtroProductos, Model modelo, HttpSession sesion) {
        String goTo = "redirect:/salir";

        if (!comprobarSesion(sesion)) {
            List<ProductsDTO> listaProductos = this.productsService.listarProductosEnVenta(filtroProductos);
            List<CategoriesDTO> listaCategorias = this.categoriesService.listarCategorias();

            BidsDTO puja = new BidsDTO();

            modelo.addAttribute("listaProductos", listaProductos);
            modelo.addAttribute("listaCategorias", listaCategorias);
            modelo.addAttribute("puja", puja);
            modelo.addAttribute("filtroProductos", filtroProductos);

            goTo = "comprador/comprador_productos_en_venta";
        }

        return goTo;
    }

    @PostMapping("/pujados/filtrar")
    public String doFiltrarProductosPujados(@ModelAttribute("filtroProductos") ProductsFilter filtroProductos, Model modelo, HttpSession sesion) {
        String goTo = "redirect:/salir";

        if (!comprobarSesion(sesion)) {
            UsersDTO comprador = (UsersDTO) sesion.getAttribute("usuario");
            List<ProductsDTO> listaProductos = this.productsService.listarProductosPujados(comprador, filtroProductos);
            List<CategoriesDTO> listaCategorias = this.categoriesService.listarCategorias();

            BidsDTO puja = new BidsDTO();

            modelo.addAttribute("listaProductos", listaProductos);
            modelo.addAttribute("listaCategorias", listaCategorias);
            modelo.addAttribute("puja", puja);
            modelo.addAttribute("filtroProductos", filtroProductos);

            goTo = "comprador/comprador_productos_pujados";
        }

        return goTo;
    }

}
