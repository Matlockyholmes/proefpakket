package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.domain.Bestelling;
import be.vdab.proefpakket.domain.Brouwer;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.GemeenteService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("proefpakket")
@SessionAttributes("bestelling")
public class BestellingController {

    private final GemeenteService gemeenteService;
    private final BestellingService bestellingService;

    public BestellingController(GemeenteService gemeenteService, BestellingService bestellingService) {
        this.gemeenteService = gemeenteService;
        this.bestellingService = bestellingService;
    }

    @GetMapping("aanvragen/{optionalBrouwer}")
    public ModelAndView bestellingStap1(@PathVariable Optional<Brouwer> optionalBrouwer){
        ModelAndView modelAndView = new ModelAndView("bestelling")
                .addObject("bestelling", new Bestelling());
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
        return modelAndView;
    }

    @InitBinder("bestelling")
    void initBinder(DataBinder dataBinder){
        dataBinder.initDirectFieldAccess();
    }

    @PostMapping(value = "aanvragen/{optionalBrouwer}", params = "stap2")
    public ModelAndView naarStap2(@Validated(Bestelling.Stap1.class) Bestelling bestelling, Errors errors, @PathVariable Optional<Brouwer> optionalBrouwer){
        ModelAndView modelAndView;
        if(optionalBrouwer.isPresent()) {

           if (errors.hasErrors()) {
               modelAndView = new ModelAndView("bestelling");
           } else {
               modelAndView = new ModelAndView("bestellingStap2").addObject(optionalBrouwer.get())
                       .addObject("gemeentes", gemeenteService.findAll());
           }
       }
        modelAndView = new ModelAndView("bestellingStap2");
//        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
        return modelAndView;
    }

    @PostMapping(value = "aanvragen/{optionalBrouwer}", params = "stap1")
    public ModelAndView naarStap1(Bestelling bestelling, @PathVariable Optional<Brouwer> optionalBrouwer){
        ModelAndView modelAndView = new ModelAndView("bestelling");
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
        return modelAndView;
    }

    @PostMapping(value = "aanvragen/{optionalBrouwer}", params = "opslaan")
    public ModelAndView create(@Validated(Bestelling.Stap2.class) Bestelling bestelling, Errors errors,
                               SessionStatus sessionStatus, @PathVariable Optional<Brouwer> optionalBrouwer){
        ModelAndView modelAndView;
        if (errors.hasErrors()){
            modelAndView = new ModelAndView("bestellingStap2", "gemeentes", gemeenteService.findAll());
            optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
            return modelAndView;
        }
        bestellingService.createBestelling(bestelling);
        modelAndView = new ModelAndView("redirect:/");
        sessionStatus.setComplete();
        return modelAndView;
    }
}
