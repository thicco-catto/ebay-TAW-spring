package es.taw.ebaytaw.controller.marketing;

import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.entity.Listausuarios;
import es.taw.ebaytaw.entity.Users;
import es.taw.ebaytaw.entity.Usuarioslista;
import es.taw.ebaytaw.repository.ListausuariosRepository;
import es.taw.ebaytaw.repository.UsersRepository;
import es.taw.ebaytaw.repository.UsuarioslistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("marketing/crearLista")
public class crearListaController {

    UsersRepository usersRepository;
    @Autowired
    public void UsersRepository(UsersRepository usersRepository) { this.usersRepository = usersRepository;}
    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    ListausuariosRepository listausuariosRepository;
    @Autowired
    public void ListausuariosRepository(ListausuariosRepository listausuariosRepository) { this.listausuariosRepository = listausuariosRepository;}
    public ListausuariosRepository getListausuariosRepository() {
        return listausuariosRepository;
    }


    UsuarioslistaRepository usuarioslistaRepository;
    @Autowired
    public void UsuarioslistaRepository(UsuarioslistaRepository usuarioslistaRepository) { this.usuarioslistaRepository = usuarioslistaRepository;}
    public UsuarioslistaRepository getUsuarioslistaRepository() {
        return usuarioslistaRepository;
    }



    @GetMapping("/")
    public String doMostrar(){
        return "marketing/marketing_crear_lista";
    }

    //Este metodo se llama desde CrearLista
    @PostMapping("/crear")
    public String doCrear (@RequestParam("fname") String fname, Model model) {

        //Creamos la nueva lista
        Listausuarios nuevaLista = listausuariosRepository.save(new Listausuarios(fname));
        //Conseguimos el id de la lista
        int idList = listausuariosRepository.getId(fname).get(0);
        //Traemos todos los usuarios
        List<Users> allUsers = usersRepository.findAll();
        List<UsersDTO> allUsersDTO = new ArrayList<>();
        for(Users u : allUsers) allUsersDTO.add(u.toDTO());
        //Traemos los usuarios de la lista
        List<UsersDTO> listUsersDTO = new ArrayList<>();


        //Model
        //Nombre de la lista
        model.addAttribute("fname",fname);
        //id de la lista
        model.addAttribute("id",idList);
        //todos los usuarios
        //Nombre de la lista
        model.addAttribute("usuarios",allUsersDTO);
        //usuarios de la lista
        //Nombre de la lista
        model.addAttribute("usuarioslista",listUsersDTO);


        return "marketing/marketing_editar_lista";

    }


    @GetMapping("/{idUser}/{idList}/addUser")
    public String doAddUser(@PathVariable("idUser") int userID, @PathVariable ("idList") int listID, Model model){

        //Get user
        Users user = usersRepository.findByUserID(userID);
        //Get list
        Listausuarios listausuarios = listausuariosRepository.findByListID(listID);
        //Add user to Listuser
        usuarioslistaRepository.save(new Usuarioslista(user,listausuarios));

        //Traemos todos los usuarios
        List<Users> allUsers = usersRepository.findAll();
        List<UsersDTO> allUsersDTO = new ArrayList<>();
        for(Users u : allUsers) allUsersDTO.add(u.toDTO());
        //Traemos los usuarios de la lista
        List<Users> listUsers = usuarioslistaRepository.getUsuariosEnUnaLista(listID);
        List<UsersDTO> listUsersDTO = new ArrayList<>();
        for(Users u : listUsers) listUsersDTO.add(u.toDTO());
        //Quitamos los usuarios añadidos
        List<UsersDTO> usuariosRestantes = new ArrayList<>();
        usuariosRestantes.addAll(allUsersDTO);
        for(UsersDTO ul : allUsersDTO )
            for(UsersDTO u : listUsersDTO )
                if(ul.getUserID() == u.getUserID())
                    usuariosRestantes.remove(ul);

        //Model
        //Nombre de la lista
        model.addAttribute("fname",listausuarios.getUsername());
        //id de la lista
        model.addAttribute("id",listID);
        //usuarios restantes
        model.addAttribute("usuarios",usuariosRestantes);
        //usuarios de la lista
        model.addAttribute("usuarioslista",listUsersDTO);


        return "marketing/marketing_editar_lista";
    }



    @GetMapping("/{idUser}/{idList}/deleteUser")
    public String doDeleteUser(@PathVariable("idUser") int userID, @PathVariable ("idList") int listID, Model model){

        //Get user
        Users user = usersRepository.findByUserID(userID);
        //Get list
        Listausuarios listausuarios = listausuariosRepository.findByListID(listID);
        //Get usuarolista
        Usuarioslista usuarioslistaBorrar = usuarioslistaRepository.findByUserIDAndAndListID(user,listausuarios);
        //Delete user to Listuser
        usuarioslistaRepository.delete(usuarioslistaBorrar);


        //Traemos todos los usuarios
        List<Users> allUsers = usersRepository.findAll();
        List<UsersDTO> allUsersDTO = new ArrayList<>();
        for(Users u : allUsers) allUsersDTO.add(u.toDTO());
        //Traemos los usuarios de la lista
        List<Users> listUsers = usuarioslistaRepository.getUsuariosEnUnaLista(listID);
        List<UsersDTO> listUsersDTO = new ArrayList<>();
        for(Users u : listUsers) listUsersDTO.add(u.toDTO());
        //Quitamos los usuarios añadidos
        List<UsersDTO> usuariosRestantes = new ArrayList<>();
        usuariosRestantes.addAll(allUsersDTO);
        for(UsersDTO ul : allUsersDTO )
            for(UsersDTO u : listUsersDTO )
                if(ul.getUserID() == u.getUserID())
                    usuariosRestantes.remove(ul);

        //Model
        //Nombre de la lista
        model.addAttribute("fname",listausuariosRepository.findByListID(listID).getUsername());
        //id de la lista
        model.addAttribute("id",listID);
        //usuarios restantes
        model.addAttribute("usuarios",usuariosRestantes);
        //usuarios de la lista
        model.addAttribute("usuarioslista",listUsersDTO);



        return "marketing/marketing_editar_lista";

    }


    //Este metodo se llama desde CrearLista
    @GetMapping("{id}/editarDesdeVerListas")
    public String doEditarDesdeVerListas (@PathVariable("id") int listID, Model model) {

        //Creamos la nueva lista
        Listausuarios nuevaLista = listausuariosRepository.findByListID(listID);
        //Conseguimos el nombre de la lista
        String fname = nuevaLista.getUsername();
        //Traemos todos los usuarios
        List<Users> allUsers = usersRepository.findAll();
        List<UsersDTO> allUsersDTO = new ArrayList<>();
        for(Users u : allUsers) allUsersDTO.add(u.toDTO());
        //Traemos los usuarios de la lista
        List<UsersDTO> listUsersDTO = new ArrayList<>();


        //Model
        //Nombre de la lista
        model.addAttribute("fname",fname);
        //id de la lista
        model.addAttribute("id",listID);
        //todos los usuarios
        //Nombre de la lista
        model.addAttribute("usuarios",allUsersDTO);
        //usuarios de la lista
        //Nombre de la lista
        model.addAttribute("usuarioslista",listUsersDTO);


        return "marketing/marketing_editar_lista";

    }




    //Este metodo se llama desde CrearLista
    //TODO:HAY QUE HACERLO NO FUNCIONA
    @PostMapping("/filtrar")
    public String doFiltrar (@RequestParam("id") int listID,
                             @RequestParam("usuariosAdded") List<UsersDTO> usersAddedDTO,
                             @RequestParam("OrderBy") String OrderBy,
                             @RequestParam("NombreUsuario") String NombreUsuario, Model model) {


        //Hacemos el filtrado
        List<Users> allUsers = usersRepository.findAll();
        List<UsersDTO> allUsersDTO = new ArrayList<>();
        for(Users u : allUsers) allUsersDTO.add(u.toDTO());










        //Traemos los usuarios de la lista
        List<Users> listUsers = usuarioslistaRepository.getUsuariosEnUnaLista(listID);
        List<UsersDTO> listUsersDTO = new ArrayList<>();
        for(Users u : listUsers) listUsersDTO.add(u.toDTO());
        //Quitamos los usuarios añadidos
        List<UsersDTO> usuariosRestantes = new ArrayList<>();
        usuariosRestantes.addAll(allUsersDTO);
        for(UsersDTO ul : allUsersDTO )
            for(UsersDTO u : listUsersDTO )
                if(ul.getUserID() == u.getUserID())
                    usuariosRestantes.remove(ul);


        //Model
        //Nombre de la lista
        model.addAttribute("fname",listausuariosRepository.findByListID(listID).getUsername());
        //id de la lista
        model.addAttribute("id",listID);
        //todos los usuarios
        //Nombre de la lista
        model.addAttribute("usuarios",allUsersDTO);
        //usuarios de la lista
        //Nombre de la lista
        model.addAttribute("usuarioslista",listUsersDTO);


        return "marketing/marketing_editar_lista";

    }


    @GetMapping("{id}/borrar")
    public String doBorrarLista (@PathVariable("id") int listID){

        Listausuarios listaToDelete = this.listausuariosRepository.findByListID(listID);
        List<Usuarioslista> UsuarioListaToDelete = this.usuarioslistaRepository.findAllByListID(listaToDelete);

        for(Usuarioslista u : UsuarioListaToDelete) this.usuarioslistaRepository.delete(u);
        this.listausuariosRepository.delete(listaToDelete);


        return "redirect:marketing/marketing_menu";

    }

}
