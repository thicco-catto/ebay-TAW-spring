package es.taw.ebaytaw.controller.marketing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("marketing/Controller")
public class marketingController {

    @GetMapping("/menu")
    public String goMenu(){
        return "redirect:/marketing/marketing_menu";
    }




}
