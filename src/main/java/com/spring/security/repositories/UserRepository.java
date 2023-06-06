package com.spring.security.repositories;

import com.spring.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(  String username );

}
