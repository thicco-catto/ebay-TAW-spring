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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class loginController {

    /*
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
*/

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
    public String doInit () {
        return "login";
    }


    @PostMapping("/autentica")
    public String doAutentica (Model model, HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {
        String goTo = "redirect:/login";

        List<UsersDTO> usuarios = this.us.listarUsuarios();
        UsersDTO usuario = this.us.comprobarCredenciales(email, password);

        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            goTo = "login";

        } else {
            session.setAttribute("usuario", usuario);

            switch (usuario.getRol()) {
                case "Marketing":
                    goTo = "marketing";
                    break;
                case "Administrador":
                    goTo = "redirect:/administrador/usuarios";
                    break;
                case "Comprador":
                    goTo = "redirect:/comprador/";
                    break;
            }
        }

        return goTo;
    }

    @GetMapping("/salir")
    public String doExit (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
