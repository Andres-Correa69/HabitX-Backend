package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.Recomendacion;
import co.edu.uniquindio.habitx.repositories.RecomendacionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    @GetMapping
    public ResponseEntity<List<Recomendacion>> listarRecomendaciones() {
        return new ResponseEntity<>(recomendacionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recomendacion> obtenerRecomendacion(@PathVariable Integer id) {
        Optional<Recomendacion> recomendacionOptional = recomendacionRepository.findById(id);
        return recomendacionOptional.map(recomendacion -> new ResponseEntity<>(recomendacion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Recomendacion> crearRecomendacion(@Valid @RequestBody Recomendacion recomendacion) {
        Recomendacion nuevaRecomendacion = recomendacionRepository.save(recomendacion);
        return new ResponseEntity<>(nuevaRecomendacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recomendacion> actualizarRecomendacion(@PathVariable Integer id, @Valid @RequestBody Recomendacion recomendacionActualizada) {
        Optional<Recomendacion> recomendacionOptional = recomendacionRepository.findById(id);
        if (recomendacionOptional.isPresent()) {
            recomendacionActualizada.setIdRecomendacion(id);
            Recomendacion recomendacionGuardada = recomendacionRepository.save(recomendacionActualizada);
            return new ResponseEntity<>(recomendacionGuardada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecomendacion(@PathVariable Integer id) {
        if (recomendacionRepository.existsById(id)) {
            recomendacionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}