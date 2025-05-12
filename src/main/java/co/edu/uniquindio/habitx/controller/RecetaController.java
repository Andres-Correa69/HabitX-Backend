package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.Receta;
import co.edu.uniquindio.habitx.repositories.RecetaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private RecetaRepository recetaRepository;

    @GetMapping
    public ResponseEntity<List<Receta>> listarRecetas() {
        return new ResponseEntity<>(recetaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerReceta(@PathVariable Integer id) {
        Optional<Receta> recetaOptional = recetaRepository.findById(id);
        return recetaOptional.map(receta -> new ResponseEntity<>(receta, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Receta> crearReceta(@Valid @RequestBody Receta receta) {
        Receta nuevaReceta = recetaRepository.save(receta);
        return new ResponseEntity<>(nuevaReceta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizarReceta(@PathVariable Integer id, @Valid @RequestBody Receta recetaActualizada) {
        Optional<Receta> recetaOptional = recetaRepository.findById(id);
        if (recetaOptional.isPresent()) {
            recetaActualizada.setIdReceta(id);
            Receta recetaGuardada = recetaRepository.save(recetaActualizada);
            return new ResponseEntity<>(recetaGuardada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Integer id) {
        if (recetaRepository.existsById(id)) {
            recetaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}