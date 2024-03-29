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
    public ModelAndView naarStap2(@Validated(Bestelling.Stap1.class) Bestelling bestelling, Errors errors, @PathVariable Optional<Brouwer> optionalBrouwer)
        {
            if (optionalBrouwer.isPresent()) {
                Brouwer brouwer = optionalBrouwer.get();
                if (errors.hasErrors()) {
                    return new ModelAndView("bestelling").addObject(brouwer);
                }
                return new ModelAndView("bestellingStap2").addObject(brouwer)
                        .addObject("gemeentes", gemeenteService.findAll());
            }
            return new ModelAndView("bestellingStap2");
        }

    @PostMapping(value = "aanvragen/{optionalBrouwer}", params = "stap1")
    public ModelAndView naarStap1(Bestelling bestelling, @PathVariable Optional<Brouwer> optionalBrouwer){
        ModelAndView modelAndView = new ModelAndView("bestelling");
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
        return modelAndView;
    }

    @PostMapping(value = "aanvragen/{brouwer}", params = "opslaan")
    public ModelAndView create(@Validated(Bestelling.Stap2.class) Bestelling bestelling, Errors errors,
                               SessionStatus sessionStatus, @PathVariable Brouwer brouwer){
        if (errors.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("bestellingStap2", "gemeentes", gemeenteService.findAll())
                    .addObject("brouwer", brouwer);
            return modelAndView;
        }
        bestellingService.createBestelling(bestelling);
        sessionStatus.setComplete();
        return new ModelAndView("redirect:/");
    }
}
