package com.proyecto1.gestionCursos.controller;

import com.proyecto1.gestionCursos.entities.Rol;
import com.proyecto1.gestionCursos.entities.User;
import com.proyecto1.gestionCursos.repository.RolRepository;
import com.proyecto1.gestionCursos.repository.UserRepository;
import com.proyecto1.gestionCursos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.HashSet;

@Controller
public class userController {

    @Autowired
    UserService userService;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/crear-usuario")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("user", new User());
        return "usuario_form"; // Muestra el formulario de creación de usuario
    }

    @PostMapping("/crear-usuario")
    public String crearUsuario(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

        try{
            String rawPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encodedPassword);

            user.setEnabled(true);

            Rol rol = rolRepository.findByNombre("CREATOR");

            // Verificar si el rol existe
            if ( rol != null) {
                user.setRoles(new HashSet<>(Collections.singletonList(rol)));
                userService.crearUsuario(user,"CREATOR");
                redirectAttributes.addFlashAttribute("message", "El usuario ha sido creado con éxito");
                return "redirect:/login";
            } else {
            // Manejar la situación en la que el rol CREATOR no existe
            redirectAttributes.addFlashAttribute("error", "No se pudo encontrar el rol CREATOR");
            return "redirect:/crear-usuario";
            }
        }catch (Exception e){
            // Manejar cualquier excepción que pueda ocurrir durante la creación del usuario
            redirectAttributes.addFlashAttribute("error", "Error al crear el usuario: " + e.getMessage());
            return "redirect:/crear-usuario";
        }
    }
}
