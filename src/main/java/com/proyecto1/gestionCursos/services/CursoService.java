package com.proyecto1.gestionCursos.services;

import com.proyecto1.gestionCursos.entities.Curso;

import java.util.List;

public interface CursoService {

    public List<Curso> listarCursos(String keyword, int page, int size);

    void crearCurso(Curso curso);

    Curso obtenerCursoPorId(Integer id);

    void eliminarCurso(Integer id);

    Long contarCursos(String keyword);

    Integer calcularTotalPaginas(String keyword, int size);
}
