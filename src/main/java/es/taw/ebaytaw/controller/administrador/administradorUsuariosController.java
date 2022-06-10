package es.taw.ebaytaw.controller.administrador;

import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.repository.UsersRepository;
import es.taw.ebaytaw.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/administrador/usuarios")
public class administradorUsuariosController {
    protected UsersService us;

    @Autowired
    public void setUsersService(UsersService usersService, UsersRepository usersRepository) {
        this.us = usersService;
        this.us.setUf(usersRepository);
    }

    @GetMapping("")
    public String showUsers(Model model){
        List<UsersDTO> users = us.listarUsuarios();
        model.addAttribute("users", users);

        UsersDTO userFilter = new UsersDTO();
        model.addAttribute("userFilter", userFilter);

        return "/administrador/administrador_usuarios";
    }

    @PostMapping("/filtrar")
    public String showFiltered(Model model, @ModelAttribute("userFilter") UsersDTO userFilter){
        List<UsersDTO> users = us.listarUsuarios(userFilter);
        model.addAttribute("users", users);

        model.addAttribute("userFilter", userFilter);

        return "/administrador/administrador_usuarios";
    }
}
