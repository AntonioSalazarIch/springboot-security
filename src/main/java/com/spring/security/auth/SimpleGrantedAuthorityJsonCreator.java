package com.spring.security.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */
public abstract class SimpleGrantedAuthorityJsonCreator {

    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator( @JsonProperty( "role" ) String role ){

    }
}
