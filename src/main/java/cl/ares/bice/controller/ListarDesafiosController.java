package cl.ares.bice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ares.bice.ws.servicio.ListarDesafios;
import cl.ares.bice.service.ListarDesafiosService;
import cl.ares.bice.ws.servicio.ListarDesafiosResponse;




@RestController
public class ListarDesafiosController {

    private final ListarDesafiosService listarDesafiosService;
    
    public ListarDesafiosController(final ListarDesafiosService listarDesafiosService) {
    	this.listarDesafiosService = listarDesafiosService;
    }

 
    @PostMapping(value = "/listarDesafios", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListarDesafiosResponse ListarDesafios(@RequestBody ListarDesafios ld) {
        return listarDesafiosService.listarDesafios(ld);
    }   
    
}