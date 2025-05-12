package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.PerfilNutricional;
import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import co.edu.uniquindio.habitx.model.Usuario;
import co.edu.uniquindio.habitx.repositories.PerfilNutricionalRepository;
import co.edu.uniquindio.habitx.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilNutricionalController {

    @Autowired
    private PerfilNutricionalRepository perfilNutricionalRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<PerfilNutricional> getAllPerfilesNutricionales() {
        return perfilNutricionalRepository.findAll();
    }
    @GetMapping("/{id}")
    public PerfilNutricional getPerfilNutricionalById(@PathVariable Integer id) {
        return perfilNutricionalRepository.findById(id).orElse(null);
    }


    @PostMapping
    public PerfilNutricional createPerfilNutricional(@RequestBody PerfilNutricional perfilNutricional) {
        // 1. Obtener el id del usuario desde el perfil recibido
        Integer idUsuario = perfilNutricional.getUsuario().getIdUsuario();

        // 2. Buscar el usuario completo en la base de datos
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Asociar el usuario al perfil nutricional
        perfilNutricional.setUsuario(usuario);

        // 4. Guardar el perfil nutricional
        PerfilNutricional perfilGuardado = perfilNutricionalRepository.save(perfilNutricional);

        // 5. Asociar el perfil al usuario y guardar el usuario
        usuario.setPerfilNutricional(perfilGuardado);
        usuarioRepository.save(usuario);

        // 6. Retornar el perfil guardado
        return perfilGuardado;
    }

    @PutMapping("/{id}")
    public PerfilNutricional updatePerfilNutricional(@PathVariable Integer id, @Valid @RequestBody PerfilNutricional perfilNutricional) {
        PerfilNutricional existingPerfil = perfilNutricionalRepository.findById(id).orElse(null);
        if (existingPerfil != null) {
            existingPerfil.setPeso(perfilNutricional.getPeso());
            existingPerfil.setAltura(perfilNutricional.getAltura());
            existingPerfil.setImc(perfilNutricional.getImc());
            return perfilNutricionalRepository.save(existingPerfil);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePerfilNutricional(@PathVariable Integer id) {
        try {
            PerfilNutricional perfil = perfilNutricionalRepository.findById(id).orElse(null);
            if (perfil != null) {
                Usuario usuario = perfil.getUsuario();
                if (usuario != null) {
                    usuario.setPerfilNutricional(null);
                    usuarioRepository.save(usuario);
                }
                perfilNutricionalRepository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace(); // O usa un logger
            throw e; // Para ver el error en el log de Spring
        }
    }
}