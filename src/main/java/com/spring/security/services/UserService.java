package com.spring.security.services;

import com.spring.security.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */
public interface UserService {
    List<User> findAll();

    Optional<User> findById( Long id );

    User save( User user );

    Optional<User> update( User user, Long id );

    void remove( Long id );

}
