package co.edu.uniquindio.habitx.controller;



import co.edu.uniquindio.habitx.model.CuentaUsuario;
import co.edu.uniquindio.habitx.repositories.CuentaUsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentaUsuario")
public class CuentaUsuarioController {

    @Autowired
    private CuentaUsuarioRepository cuentaUsuarioRepository;

    @GetMapping
    public ResponseEntity<List<CuentaUsuario>> listarCuentasUsuario() {
        return new ResponseEntity<>(cuentaUsuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}") // Modificamos la ruta para indicar que el ID es de Usuario
    public ResponseEntity<CuentaUsuario> obtenerCuentaUsuarioPorIdUsuario(@PathVariable Integer idUsuario) {
        Optional<CuentaUsuario> cuentaUsuarioOptional = cuentaUsuarioRepository.findByUsuarioIdUsuario(idUsuario);
        return cuentaUsuarioOptional.map(cuentaUsuario -> new ResponseEntity<>(cuentaUsuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaUsuario> obtenerCuentaUsuario(@PathVariable Integer id) {
        Optional<CuentaUsuario> cuentaUsuarioOptional = cuentaUsuarioRepository.findById(id);
        return cuentaUsuarioOptional.map(cuentaUsuario -> new ResponseEntity<>(cuentaUsuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CuentaUsuario> crearCuentaUsuario(@Valid @RequestBody CuentaUsuario cuentaUsuario) {
        CuentaUsuario nuevaCuentaUsuario = cuentaUsuarioRepository.save(cuentaUsuario);
        return new ResponseEntity<>(nuevaCuentaUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaUsuario> actualizarCuentaUsuario(@PathVariable Integer id, @Valid @RequestBody CuentaUsuario cuentaUsuarioActualizada) {
        Optional<CuentaUsuario> cuentaUsuarioOptional = cuentaUsuarioRepository.findById(id);
        if (cuentaUsuarioOptional.isPresent()) {
            cuentaUsuarioActualizada.setIdCuentaUsuario(id);
            CuentaUsuario cuentaUsuarioGuardada = cuentaUsuarioRepository.save(cuentaUsuarioActualizada);
            return new ResponseEntity<>(cuentaUsuarioGuardada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaUsuario(@PathVariable Integer id) {
        if (cuentaUsuarioRepository.existsById(id)) {
            cuentaUsuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}