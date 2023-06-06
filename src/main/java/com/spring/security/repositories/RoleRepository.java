package com.spring.security.repositories;

import com.spring.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName( String name );
}
