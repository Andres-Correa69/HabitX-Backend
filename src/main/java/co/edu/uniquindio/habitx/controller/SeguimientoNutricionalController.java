package co.edu.uniquindio.habitx.controller;



import co.edu.uniquindio.habitx.model.SeguimientoNutricional;
import co.edu.uniquindio.habitx.repositories.SeguimientoNutricionalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seguimientoNutricional" )
public class SeguimientoNutricionalController {

    @Autowired
    private SeguimientoNutricionalRepository seguimientoNutricionalRepository;

    @GetMapping
    public ResponseEntity<List<SeguimientoNutricional>> listarSeguimientosNutricionales() {
        return new ResponseEntity<>(seguimientoNutricionalRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoNutricional> obtenerSeguimientoNutricional(@PathVariable Integer id) {
        Optional<SeguimientoNutricional> seguimientoNutricionalOptional = seguimientoNutricionalRepository.findById(id);
        return seguimientoNutricionalOptional.map(seguimiento -> new ResponseEntity<>(seguimiento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SeguimientoNutricional> crearSeguimientoNutricional(@Valid @RequestBody SeguimientoNutricional seguimientoNutricional) {
        SeguimientoNutricional nuevoSeguimiento = seguimientoNutricionalRepository.save(seguimientoNutricional);
        return new ResponseEntity<>(nuevoSeguimiento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoNutricional> actualizarSeguimientoNutricional(@PathVariable Integer id, @Valid @RequestBody SeguimientoNutricional seguimientoNutricionalActualizado) {
        Optional<SeguimientoNutricional> seguimientoNutricionalOptional = seguimientoNutricionalRepository.findById(id);
        if (seguimientoNutricionalOptional.isPresent()) {
            seguimientoNutricionalActualizado.setIdSeguimientoNutricional(id);
            SeguimientoNutricional seguimientoGuardado = seguimientoNutricionalRepository.save(seguimientoNutricionalActualizado);
            return new ResponseEntity<>(seguimientoGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeguimientoNutricional(@PathVariable Integer id) {
        if (seguimientoNutricionalRepository.existsById(id)) {
            seguimientoNutricionalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}