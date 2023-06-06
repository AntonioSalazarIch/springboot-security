package com.spring.security.auth;

import com.spring.security.auth.filters.JwtAuthenticacionFilter;
import com.spring.security.auth.filters.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    // tiene una encriptacion de una sola via, no se puede desencriptar
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain filterChain( HttpSecurity http ) throws Exception{
        return http.authorizeHttpRequests()
                .requestMatchers( HttpMethod.GET, "/*" ).permitAll()
                .requestMatchers( HttpMethod.POST, "/users" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter( new JwtAuthenticacionFilter( authenticationConfiguration.getAuthenticationManager() ) ) // le pasamos una instancia de nuestra clase filtro 'JwtAuthenticacionFilter'
                .addFilter( new JwtValidationFilter( authenticationConfiguration.getAuthenticationManager() ) ) // le pasamos una instancia de nuestra clase filtro 'JwtValidationFilter'
                .csrf( config -> config.disable() ) //se desabilita cuando se usa como rest, el frontend maneja esto por debajo y solo se habilita en monolito
                .sessionManagement( managment -> managment.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) ) // la sesion se maneja por peticion
                .build();
    }


}
