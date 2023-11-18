package com.proyecto1.gestionCursos.controller;

import com.proyecto1.gestionCursos.entities.Curso;
import com.proyecto1.gestionCursos.reports.CursoExporterExcel;
import com.proyecto1.gestionCursos.reports.CursoExporterPDF;
import com.proyecto1.gestionCursos.repository.CursoRepository;
import com.proyecto1.gestionCursos.services.CursoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class cursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/")
    public String home(){
        return "redirect:/cursos";
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model, @Param("keyword") String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){

        try {
            // Llamada al servicio para obtener la lista de cursos
            List<Curso> cursos = cursoService.listarCursos(keyword, page, size);

            // Agregar atributos al modelo
            model.addAttribute("cursos", cursos);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalItems", cursoService.contarCursos(keyword));
            model.addAttribute("totalPages", cursoService.calcularTotalPaginas(keyword, size));
            model.addAttribute("pageSize", size);
            model.addAttribute("keyword", keyword);

        } catch (Exception exception) {

            model.addAttribute("message", exception.getMessage());
        }

        return "cursos";
    }

    @GetMapping("/cursos/nuevo")
    public String mostrarFormularioNuevoCurso(Model model){
        Curso curso = new Curso();
        curso.setPublicado(true);

        model.addAttribute("curso",curso);
        model.addAttribute("pageTitle","Nuevo curso");
        return "curso_form";
    }

    @PostMapping("/cursos/save")
    public String crearCurso(@ModelAttribute("curso") Curso curso, RedirectAttributes redirectAttributes){
        try{
            cursoService.crearCurso(curso);
            redirectAttributes.addFlashAttribute("message","El curso ha sido guardado con éxito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/cursos";
    }

    @GetMapping("/cursos/{id}")
    public String editarCurso(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Curso curso = cursoService.obtenerCursoPorId(id);

            model.addAttribute("curso", curso);
            model.addAttribute("pageTitle", "Editar curso: " + id);
            return "curso_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/cursos";
        }
    }

    @GetMapping("/cursos/delete/{id}")
    public String eliminarCurso(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        try{
            cursoService.eliminarCurso(id);
            redirectAttributes.addFlashAttribute("message","El curso ha sido eliminado");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/cursos";
    }

    @GetMapping("/export/pdf")
    public void generarReporte(HttpServletResponse response, @RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) throws IOException {

        System.out.println("Keyword: " + keyword);
        System.out.println("Page: " + page);
        System.out.println("Size: " + size);
        try {
            response.setContentType("application/pdf");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=cursos" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            List<Curso> cursos;

            if (keyword == null || keyword.isEmpty()) {
                cursos = cursoService.listarCursos(null, 1, Integer.MAX_VALUE);
            } else {
                cursos = cursoService.listarCursos(keyword, 1, Integer.MAX_VALUE);
            }


            CursoExporterPDF exporterPDF = new CursoExporterPDF(cursos);
            exporterPDF.export(response);
        } catch (Exception e) {
            // Manejar excepciones según tus necesidades
            e.printStackTrace();
            // Puedes agregar un mensaje de error a tu modelo si lo necesitas
        }
    }

    @GetMapping("/export/excel")
    public void generarReporteExcel(HttpServletResponse response, @RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=cursos" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Curso> cursos;

        if (keyword == null || keyword.isEmpty()) {
            cursos = cursoService.listarCursos(null, 1, Integer.MAX_VALUE);
        } else {
            cursos = cursoService.listarCursos(keyword, 1, Integer.MAX_VALUE);
        }

        CursoExporterExcel exporterExcel = new CursoExporterExcel(cursos);
        exporterExcel.export(response);
    }

}
