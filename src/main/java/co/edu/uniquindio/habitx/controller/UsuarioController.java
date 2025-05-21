package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.CuentaUsuario;
import co.edu.uniquindio.habitx.model.Usuario;
import co.edu.uniquindio.habitx.repositories.CuentaUsuarioRepository;
import co.edu.uniquindio.habitx.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CuentaUsuarioRepository cuentaUsuarioRepository;

    @GetMapping("/sql/getusuarios")
    public List<Usuario>  obtenerUsuariosSQL() {

        return usuarioRepository.obtenerTodosLosUsuariosSQL();
    }
    @GetMapping("/sql/getusuarios/perfiles")
    public List<Usuario>  obtenerUsuariosSQLperfiles(){

        return usuarioRepository.obtenerTodosLosUsuariosSQLperfiles();
    }

    @GetMapping("/sql/getusuarios/genero")
    public List<Usuario>  obtenerUsuariosMasculinosSQL() {

        return usuarioRepository.obtenerUsuariosMasculinosSQL();
    }

    @GetMapping("/sql/getusuarios/promedioIMC")
    public List<Usuario>  obtenerUsuariosMayorPromedioIMC() {

        return usuarioRepository.obtenerUsuariosMayorPromedioIMC();
    }

    @GetMapping("/sql/getusuarios/promedioPesoGenero")
    public List<Usuario>  obtenerUsuariosMayorPromedioPesoGeneroSQL() {

        return usuarioRepository.obtenerUsuariosMayorPromedioPesoGenero();
    }


    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
        // 1. Guardar el nuevo usuario
        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // 2. Crear una nueva CuentaUsuario y asociarla al usuario creado
        CuentaUsuario nuevaCuentaUsuario = new CuentaUsuario();
        nuevaCuentaUsuario.setUsuario(nuevoUsuario);

        // 3. Guardar la nueva CuentaUsuario
        cuentaUsuarioRepository.save(nuevaCuentaUsuario);

        // 4. Devolver el usuario creado con un código de éxito
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElse(null);
        if (existingUsuario != null) {
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            existingUsuario.setEmail(usuario.getEmail());
            existingUsuario.setEdad(usuario.getEdad());
            existingUsuario.setGenero(usuario.getGenero());
            existingUsuario.setLogin(usuario.getLogin());
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}
