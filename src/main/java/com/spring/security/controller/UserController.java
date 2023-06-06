package com.spring.security.controller;

import com.spring.security.entities.User;
import com.spring.security.services.UserService;
import com.spring.security.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */

@RestController
@RequestMapping( "/users" )
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping( "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id ){
        Optional<User> userOptional = userService.findById( id );
        if( userOptional.isPresent() ){
            return ResponseEntity.ok( userOptional.orElseThrow() );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save( @RequestBody User user ){
        return ResponseEntity.status( HttpStatus.CREATED ).body( userService.save( user ) );
    }

    @PutMapping( "/{id}")
    public ResponseEntity<?> update( @RequestBody User user, @PathVariable Long id ){
        Optional<User> userOptional = userService.update( user, id );
        if( userOptional.isPresent() ){
            return ResponseEntity.status( HttpStatus.CREATED ).body( userOptional.orElseThrow() );
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> deleteById( @PathVariable Long id ){
        Optional<User> optionalUser = userService.findById( id );

        if( optionalUser.isPresent() ){
            userService.remove( id );
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
