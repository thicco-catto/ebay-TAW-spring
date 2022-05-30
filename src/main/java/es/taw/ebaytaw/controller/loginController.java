package es.taw.ebaytaw.controller;

import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {

    UsersService us;


    @GetMapping("/")
    public String doInit () {        return "login";     }


    @PostMapping("/autentica")
    public String doAutentica (Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        String goTo = "redirect:/customer/";

        UsersDTO usuario = this.us.comprobarCredenciales(email, password);

        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            goTo = "login";
        }

        return goTo;
    }




}
