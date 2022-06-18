package es.taw.ebaytaw.controller.comprador;

import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comprador")
public class CompradorController {
    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String doInicio() {
        return "redirect:/comprador/productos/";
    }

    @GetMapping("/registro")
    public String doRegistroComprador(Model modelo) {
        UsersDTO comprador = new UsersDTO();

        modelo.addAttribute("comprador", comprador);

        return "comprador/comprador_registro";
    }

    @PostMapping("/registrar")
    public String doRegistrarComprador(@ModelAttribute("comprador") UsersDTO comprador) {
        this.usersService.guardarComprador(comprador);

        return "redirect:/";
    }
}
