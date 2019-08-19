package be.vdab.proefpakket.restservices;

import be.vdab.proefpakket.domain.Gemeente;
import be.vdab.proefpakket.exceptions.GemeenteNietGevondenException;
import be.vdab.proefpakket.services.GemeenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gemeenten")
public class GemeenteRestController {
    private final GemeenteService gemeenteService;

    public GemeenteRestController(GemeenteService gemeenteService) {
        this.gemeenteService = gemeenteService;
    }

    @GetMapping("{gemeente}")
    public Gemeente get(@PathVariable Optional<Gemeente> gemeente){
        if (gemeente.isPresent()){
            return gemeente.get();
        }
        throw new GemeenteNietGevondenException();
    }

    @ExceptionHandler(GemeenteNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void GemeenteNietGevonden(){
    }
}
