package es.taw.ebaytaw.controller.marketing;

import es.taw.ebaytaw.DTO.ListausuariosDTO;
import es.taw.ebaytaw.entity.Listausuarios;
import es.taw.ebaytaw.entity.Mensaje;
import es.taw.ebaytaw.entity.Users;
import es.taw.ebaytaw.entity.Usuarioslista;
import es.taw.ebaytaw.repository.ListausuariosRepository;
import es.taw.ebaytaw.repository.MensajeRepository;
import es.taw.ebaytaw.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("marketing/mensajes")
public class mensajesController {

    ListausuariosRepository listausuariosRepository;
    @Autowired
    public void ListausuariosRepository(ListausuariosRepository listausuariosRepository) { this.listausuariosRepository = listausuariosRepository;}
    public ListausuariosRepository getListausuariosRepository() {
        return listausuariosRepository;
    }

    MensajeRepository mensajeRepository;
    @Autowired
    public void MensajeRepository(MensajeRepository mensajeRepository) { this.mensajeRepository = mensajeRepository;}
    public MensajeRepository getMensajeRepository() {
        return mensajeRepository;
    }

    UsersRepository usersRepository;
    @Autowired
    public void UsersRepository(UsersRepository usersRepository) { this.usersRepository = usersRepository;}
    public UsersRepository getUsersRepository() {
        return usersRepository;
    }



    @GetMapping("/redactar")
    public String doRedactar(){
        return "marketing/marketing_crear_mensaje";
    }


    @PostMapping("/crear")
    public String doCrear(@RequestParam("mensaje") String mensaje, Model model){

        //Traemos todas las listas
        List<Listausuarios> listausuariosList = listausuariosRepository.findAll();
        List<ListausuariosDTO> listausuariosDTOS = new ArrayList<>();
        for(Listausuarios l : listausuariosList) listausuariosDTOS.add(l.toDTO());


        model.addAttribute("listas",listausuariosDTOS);
        model.addAttribute("textoMensaje",mensaje);


        return "marketing/marketing_elegir_listas";
    }


    @GetMapping("/{listID}/{textoMensaje}/enviar")
    public String doEnviar(@PathVariable("listID") int listID,@PathVariable("textoMensaje") String textoMensaje){

        //Creamos un mensaje para todos los usuarios de esa lista
        for(Usuarioslista u : listausuariosRepository.findByListID(listID).getUsuarioslistaList())
            this.mensajeRepository.save(new Mensaje(u.getUserID(),textoMensaje));

        return "volver_al_menu";
    }


    @GetMapping("/{mensajeID}/{textoMensaje}/borrar")
    public String doBorrar(@PathVariable("mensajeID") int mensajeID,@PathVariable("userID") int userID){

        //Encontramos el mensaje a borrar
        this.mensajeRepository.delete(this.mensajeRepository.findByUserIDAndId(this.usersRepository.findByUserID(userID),mensajeID));

        return "volver_al_menu";
    }


    @GetMapping("{userID}/misMensajes")
    public String doMisMensajes(@PathVariable("userID") int userID, Model model){

        model.addAttribute("misMensajes", this.mensajeRepository.findAllByUserID(this.usersRepository.findByUserID(userID)));

        return "marketing/marketing_ver_mis_mensajes";
    }







}
