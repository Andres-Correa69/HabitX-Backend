package co.edu.uniquindio.habitx.controller;



import co.edu.uniquindio.habitx.model.NivelCuenta;
import co.edu.uniquindio.habitx.repositories.NivelCuentaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/niveles-cuenta")
public class NivelCuentaController {

    @Autowired
    private NivelCuentaRepository nivelCuentaRepository;

    @GetMapping
    public ResponseEntity<List<NivelCuenta>> listarNivelesCuenta() {
        return new ResponseEntity<>(nivelCuentaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NivelCuenta> obtenerNivelCuenta(@PathVariable Integer id) {
        Optional<NivelCuenta> nivelCuentaOptional = nivelCuentaRepository.findById(id);
        return nivelCuentaOptional.map(nivelCuenta -> new ResponseEntity<>(nivelCuenta, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<NivelCuenta> crearNivelCuenta(@Valid @RequestBody NivelCuenta nivelCuenta) {
        NivelCuenta nuevoNivelCuenta = nivelCuentaRepository.save(nivelCuenta);
        return new ResponseEntity<>(nuevoNivelCuenta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NivelCuenta> actualizarNivelCuenta(@PathVariable Integer id, @Valid @RequestBody NivelCuenta nivelCuentaActualizado) {
        Optional<NivelCuenta> nivelCuentaOptional = nivelCuentaRepository.findById(id);
        if (nivelCuentaOptional.isPresent()) {
            nivelCuentaActualizado.setIdNivelCuenta(id);
            NivelCuenta nivelCuentaGuardado = nivelCuentaRepository.save(nivelCuentaActualizado);
            return new ResponseEntity<>(nivelCuentaGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNivelCuenta(@PathVariable Integer id) {
        if (nivelCuentaRepository.existsById(id)) {
            nivelCuentaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}