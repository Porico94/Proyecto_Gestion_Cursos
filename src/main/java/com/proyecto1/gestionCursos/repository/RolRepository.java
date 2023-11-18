package com.proyecto1.gestionCursos.repository;

import com.proyecto1.gestionCursos.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Integer> {

    Rol findByNombre(String nombre);
}
