package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.domain.Brouwer;
import be.vdab.proefpakket.forms.OndernemingsnrForm;
import be.vdab.proefpakket.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("brouwers")
class BrouwerController {

    private final BrouwerService service;

    public BrouwerController(BrouwerService service) {
        this.service = service;
    }

    @GetMapping("{optionalBrouwer}")
    public ModelAndView read(@PathVariable Optional<Brouwer> optionalBrouwer) {
        ModelAndView modelAndView = new ModelAndView("brouwer");
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
        return modelAndView;
    }
    @GetMapping("ondernemingsnr/{id}")
    public ModelAndView ondernemingsnummerView(@PathVariable ("id") Optional<Brouwer> optionalBrouwer){
        ModelAndView modelAndView = new ModelAndView("ondernemingsnr");
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer)
                .addObject(new OndernemingsnrForm(brouwer.getOndernemingsNr())));
        return modelAndView;
    }
    @PostMapping("ondernemingsnr/{optionalBrouwer}")
    public ModelAndView ondernemingsnummerInvoegen(@PathVariable Optional<Brouwer> optionalBrouwer, @Valid OndernemingsnrForm form, Errors errors){
        Brouwer brouwer = optionalBrouwer.get();
        if (errors.hasErrors()){
            return new ModelAndView("ondernemingsnr").addObject(brouwer);
        }
        brouwer.setOndernemingsNr(form.getOndernemingsnr());
        service.updateBrouwer(brouwer);
        return new ModelAndView("brouwer").addObject(brouwer);
    }
}
