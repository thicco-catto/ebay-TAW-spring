package es.taw.ebaytaw.controller.marketing;

import es.taw.ebaytaw.DTO.ListausuariosDTO;
import es.taw.ebaytaw.entity.Listausuarios;
import es.taw.ebaytaw.repository.ListausuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("marketing/verLista")
public class verListasController {

    ListausuariosRepository listausuariosRepository;
    @Autowired
    public void ListausuariosRepository(ListausuariosRepository listausuariosRepository) { this.listausuariosRepository = listausuariosRepository;}
    public ListausuariosRepository getListausuariosRepository() {
        return listausuariosRepository;
    }



    @GetMapping("/")
    public String doMostrar(Model model){


        List<Listausuarios> listausuariosList = listausuariosRepository.findAll();
        List<ListausuariosDTO> listausuariosDTOS = new ArrayList<>();
        for(Listausuarios l : listausuariosList) listausuariosDTOS.add(l.toDTO());

        model.addAttribute("listas",listausuariosDTOS);

        return "marketing/marketing_ver_listas";
    }







}
