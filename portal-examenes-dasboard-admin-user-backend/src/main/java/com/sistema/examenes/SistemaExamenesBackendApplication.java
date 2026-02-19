package com.sistema.examenes;

import com.sistema.examenes.excepciones.UsuarioFoundException;
import com.sistema.examenes.modelo.Rol;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import com.sistema.examenes.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(SistemaExamenesBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Usuario usuario = new Usuario();

            usuario.setNombre("Christian");
            usuario.setApellido("Ramirez");
            usuario.setUsername("christian");

            // Enviamos la clave normal, el servicio se encarga de encriptarla
            usuario.setPassword("12345");

            usuario.setEmail("c1@gmail.com");
            usuario.setTelefono("988212020");
            usuario.setPerfil("foto.png");

            Rol rol = new Rol();
            rol.setRolId(1L);
            rol.setRolNombre("ADMIN");

            Set<UsuarioRol> usuariosRoles = new HashSet<>();
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setRol(rol);
            usuarioRol.setUsuario(usuario);
            usuariosRoles.add(usuarioRol);

            Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuariosRoles);
            System.out.println("USUARIO ADMINISTRADOR LISTO: " + usuarioGuardado.getUsername());

        } catch (UsuarioFoundException exception) {
            System.out.println("El usuario administrador ya existe en la base de datos.");
        }
    }
}