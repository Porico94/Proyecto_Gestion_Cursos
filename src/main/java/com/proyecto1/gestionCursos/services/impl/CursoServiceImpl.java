package com.proyecto1.gestionCursos.services.impl;

import com.proyecto1.gestionCursos.entities.Curso;
import com.proyecto1.gestionCursos.repository.CursoRepository;
import com.proyecto1.gestionCursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> listarCursos(String keyword, int page, int size) {

        List<Curso> cursos = new ArrayList<>(); //Creamos una lista de cursos vacia

        try {

            Pageable paging = PageRequest.of(page-1,size); //Configuramos la paginación para la consulta de la
            //base de datos, por default es pagina 0 y la cantidad de elementos es 3

            Page<Curso> pageCursos = null; //Se crea un objeto de tipo Page donde se almacenaran los resultados paginados

            if(keyword == null){    //Si no existe un keyword, es decir no se realiza ninguna busqueda
                pageCursos = cursoRepository.findAll(paging); //Se almacena en el objeto tipo Page todos los elementos con la
                //configuración paging, es decir empezara en la pagina 0 y mostrara 3 elementos por pagina
            } else {    //Si existe un keyword, es decir se realiza una busqueda
                pageCursos = cursoRepository.findByTituloContainingIgnoreCase(keyword, paging); //guardamos todos los elementos que coinciden
                // y los almacenamos en el objeto tipo Page con la configuracion de paging
            }

            cursos = pageCursos.getContent();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void crearCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public Curso obtenerCursoPorId(Integer id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCurso(Integer id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Long contarCursos(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            // Si no hay palabra clave, contar todos los cursos
            return cursoRepository.count();
        } else {
            // Si hay una palabra clave, contar cursos que coincidan con la palabra clave
            return cursoRepository.countByTituloContainingIgnoreCase(keyword);
        }
    }

    @Override
    public Integer calcularTotalPaginas(String keyword, int size) {
        try {
            long totalItems = contarCursos(keyword); // Obtener la cantidad total de cursos
            return (int) Math.ceil((double) totalItems / size); // retornamos el total de paginas y utilizamos ceil para redondear hacia arriba

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
