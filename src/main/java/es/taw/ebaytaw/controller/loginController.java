package es.taw.ebaytaw.controller;

import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.repository.UsersRepository;
import es.taw.ebaytaw.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    protected UsersService us;

    public UsersService getUsersService() {
        return us;
    }


    @Autowired
    public void setUsersService(UsersService usersService, UsersRepository usersRepository) {
        this.us = usersService;
        this.us.setUf(usersRepository);
    }



    @GetMapping("/")
    public String doInit () {        return "login";     }


    @PostMapping("/autentica")
    public String doAutentica (Model model, HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {
        String goTo = "redirect:/comprador/productosEnVenta";

        UsersDTO usuario = this.us.comprobarCredenciales(email, password);

        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            goTo = "login";

        } else if(usuario.getRol().equals("Marketing")) {
            session.setAttribute("usuario",usuario);
            goTo = "marketing/marketing_menu";
        }

        return goTo;
    }




}
