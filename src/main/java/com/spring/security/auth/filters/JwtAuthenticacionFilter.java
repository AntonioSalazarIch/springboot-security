package com.spring.security.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.entities.User;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.spring.security.auth.TokenJwtConfig.SECRET_KEY;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */



public class JwtAuthenticacionFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;
    public JwtAuthenticacionFilter( AuthenticationManager authenticationManager ) {
        this.authenticationManager = authenticationManager;
    }

    // cuando la ruta sea /login y el metodo post se ejecuta este metodo como un interceptor
    @Override
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response )
            throws AuthenticationException {

        User user = null;
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue( request.getInputStream(), User.class );
            username = user.getUsername();
            password = user.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( username, password );
        return authenticationManager.authenticate( authToken );
    }

    // este metodo se ejecuta cuando la autenticacion es correcta
    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                             Authentication authResult ) throws IOException, ServletException {

        String username = ( ( org.springframework.security.core.userdetails.User )authResult.getPrincipal() ).getUsername();

        // generamos el token para devolver al cliente
        String token = Jwts.builder()
                .setSubject( username )
                .signWith( SECRET_KEY )
                .setIssuedAt( new Date() )
                .setExpiration( new Date( System.currentTimeMillis() + 3600000) )
                .compact();

        response.addHeader( "Authorization", "Bearer " + token );

        Map<String, Object> body = new HashMap<>();
        body.put( "token", token );
        body.put( "message", String.format( "Hola %s, has iniciado sesion con exito!", username) );
        body.put( "username", username );

        response.getWriter().write( new ObjectMapper().writeValueAsString( body ) );
        response.setStatus( 200 );
        response.setContentType( "application/json" );
    }

    // este metodo se ejecuta cuando la autenticacion falla
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();
        body.put( "message", "Error en la autenticacion, username o password incorrecto!" );
        body.put( "error", failed.getMessage() );

        response.getWriter().write( new ObjectMapper().writeValueAsString( body ) );
        response.setStatus( 401 );
        response.setContentType( "application/json" );
    }
}
