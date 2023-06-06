package com.spring.security.services;

import com.spring.security.entities.User;
import com.spring.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Antonio Esteban Salazar Ichuta <br>
 * Ende Tecnologías S.A.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional( readOnly = true )
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Optional<User> findById(Long id) {
        return userRepository.findById( id );
    }

    @Override
    @Transactional
    public User save( User user ) {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        return userRepository.save( user );
    }

    @Override
    @Transactional
    public Optional<User> update(User user, Long id) {
        Optional<User> optionalUser = findById( id );
        if( optionalUser.isPresent() ){
            User userDb = optionalUser.orElseThrow();
            userDb.setUsername( user.getUsername() );
            userDb.setEmail( user.getEmail() );
            return Optional.of( save( userDb ) );
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById( id );
    }
}
