package com.proyecto1.gestionCursos.services.impl;

import com.proyecto1.gestionCursos.entities.User;
import com.proyecto1.gestionCursos.repository.UserRepository;
import com.proyecto1.gestionCursos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> obtenerUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public User obtenerPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User crearUsuario(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deshabilitarUsuario(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEnabled(false);
            userRepository.save(user);
        }
    }

    @Override
    public long contarUsuarios() {
        return userRepository.count();
    }
}
