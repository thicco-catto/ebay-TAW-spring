package es.taw.ebaytaw.controller.vendedor;

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

@Controller
@RequestMapping("/vendedor/newvendedor")
public class vendedorNewVendedorController {

    protected UsersService us;

    @Autowired
    public void setUsersService(UsersService usersService, UsersRepository usersRepository) {
        this.us = usersService;
        this.us.setUf(usersRepository);
    }

    @GetMapping("")
    public String redirectToRegister(Model model){
        UsersDTO user = new UsersDTO();
        model.addAttribute("user", user);

        return "vendedor/vendedor_registro";
    }

    @GetMapping("/toHome")
    public String redirectToHome(){
        return "login";
    }


    @PostMapping("/guardarNuevo")
    public String saveNewUser(@ModelAttribute("user") UsersDTO user){
        this.us.crearUser(user);

        return "/vendedor/newvendedor/toHome";
    }
}
