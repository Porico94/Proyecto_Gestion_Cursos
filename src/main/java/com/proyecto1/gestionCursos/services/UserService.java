package com.proyecto1.gestionCursos.services;

import com.proyecto1.gestionCursos.entities.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    List<User> obtenerUsuarios();

    User obtenerPorId(Long id);

    User crearUsuario(User user);

    void deshabilitarUsuario(Long id);

    long contarUsuarios();

}
