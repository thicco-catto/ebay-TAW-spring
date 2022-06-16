package es.taw.ebaytaw.controller.administrador;

import es.taw.ebaytaw.DTO.CategoriesDTO;
import es.taw.ebaytaw.DTO.ProductsDTO;
import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.repository.CategoriesRepository;
import es.taw.ebaytaw.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador/categorias")
public class administradorCategoriasController {
    protected CategoriesService cs;

    @Autowired
    public void setCategoriesService(CategoriesService categoriesService, CategoriesRepository categoriesRepository){
        this.cs = categoriesService;
        this.cs.setCf(categoriesRepository);
    }

    @GetMapping("")
    public String showCategories(Model model){
        List<CategoriesDTO> categories = this.cs.findAll();

        model.addAttribute("categories", categories);

        CategoriesDTO categoryFilter = new CategoriesDTO();
        model.addAttribute("categoryFilter", categoryFilter);

        return "/administrador/administrador_categorias";
    }

    @PostMapping("/filtrar")
    public String showFilteredCategories(Model model, @ModelAttribute("CategoryFilter") CategoriesDTO categoryFilter){
        List<CategoriesDTO> categories = this.cs.listarCategorias(categoryFilter.getName());

        model.addAttribute("categories", categories);

        model.addAttribute("categoryFilter", categoryFilter);

        return "/administrador/administrador_categorias";
    }

    @GetMapping("/crear")
    public String createCategory(Model model){
        CategoriesDTO category = new CategoriesDTO();

        model.addAttribute("category", category);

        return "/administrador/administrador_crear_categoria";
    }

    @PostMapping("/guardarNueva")
    public String saveNewCategory(@ModelAttribute("category") CategoriesDTO category){
        this.cs.crearCategoria(category.getName());

        return "redirect:/administrador/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editCategory(Model model, @PathVariable("id") Integer id){
        CategoriesDTO category = this.cs.getCategoria(id);

        model.addAttribute("category", category);

        return "/administrador/administrador_editar_categoria";
    }

    @PostMapping("/guardarEditado")
    public String editCategory(@ModelAttribute("category") CategoriesDTO category){
        this.cs.editarCategoria(category.getCategoryID(), category.getName());

        return "redirect:/administrador/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        this.cs.borrarCategoria(id);

        return "redirect:/administrador/categorias";
    }
}
