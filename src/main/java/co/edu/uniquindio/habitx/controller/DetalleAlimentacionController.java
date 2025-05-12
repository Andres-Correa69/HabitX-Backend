package co.edu.uniquindio.habitx.controller;


import co.edu.uniquindio.habitx.model.DetalleAlimentacion;
import co.edu.uniquindio.habitx.repositories.DetalleAlimentacionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalleAlimentacion")
public class DetalleAlimentacionController {

    @Autowired
    private DetalleAlimentacionRepository detalleAlimentacionRepository;

    @GetMapping
    public ResponseEntity<List<DetalleAlimentacion>> listarDetallesAlimentacion() {
        return new ResponseEntity<>(detalleAlimentacionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleAlimentacion> obtenerDetalleAlimentacion(@PathVariable Integer id) {
        Optional<DetalleAlimentacion> detalleAlimentacionOptional = detalleAlimentacionRepository.findById(id);
        return detalleAlimentacionOptional.map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DetalleAlimentacion> crearDetalleAlimentacion(@Valid @RequestBody DetalleAlimentacion detalleAlimentacion) {
        DetalleAlimentacion nuevoDetalle = detalleAlimentacionRepository.save(detalleAlimentacion);
        return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleAlimentacion> actualizarDetalleAlimentacion(@PathVariable Integer id, @Valid @RequestBody DetalleAlimentacion detalleAlimentacionActualizado) {
        Optional<DetalleAlimentacion> detalleAlimentacionOptional = detalleAlimentacionRepository.findById(id);
        if (detalleAlimentacionOptional.isPresent()) {
            detalleAlimentacionActualizado.setIdDetalleAlimentacion(id);
            DetalleAlimentacion detalleGuardado = detalleAlimentacionRepository.save(detalleAlimentacionActualizado);
            return new ResponseEntity<>(detalleGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleAlimentacion(@PathVariable Integer id) {
        if (detalleAlimentacionRepository.existsById(id)) {
            detalleAlimentacionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}