package com.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */
@RestController
public class InitialController {

    //@PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping( "/test" )
    public String init( ){
        return "Sistema de autenticacion funcionando";
    }

    @GetMapping( "/test/1" )
    public String init2( ){
        return "Este es un controlador de prueba";
    }
}
