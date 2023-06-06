package com.spring.security.dto.mapper;

import com.spring.security.dto.UserDto;
import com.spring.security.entities.User;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */
public class DtoMapperUser {

    private static DtoMapperUser mapperUser;

    private User user;

    private DtoMapperUser(){
    }

    public static DtoMapperUser builder(){
        mapperUser = new DtoMapperUser();
        return mapperUser;
    }

    public DtoMapperUser setUser( User user ){
        this.user = user;
        return mapperUser;
    }

    public UserDto build(){
        if( user == null ){
            throw new RuntimeException( "Debe pasar el entity User!" );
        }

        return new UserDto( this.user.getId(), this.user.getUsername(), this.user.getEmail() );
        //return userDto;
    }
}
