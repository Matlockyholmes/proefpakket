package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.exceptions.TemperatuurNietGevondenException;
import be.vdab.proefpakket.services.OmwService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("weer")
public class TemperatuurController {
    private final OmwService omwService;

    public TemperatuurController(OmwService omwService) {
        this.omwService = omwService;
    }

    @GetMapping("{plaats}/temperatuur")
    public ModelAndView toonTemperatuur(@PathVariable String plaats){
        ModelAndView modelAndView = new ModelAndView("temperatuur");
        try{
            modelAndView.addObject("temperatuur", omwService.geefTemperatuur(plaats));
        }catch (TemperatuurNietGevondenException ex){

        }
        return modelAndView;
    }
}
