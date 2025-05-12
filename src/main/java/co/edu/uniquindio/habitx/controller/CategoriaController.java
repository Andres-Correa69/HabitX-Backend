package co.edu.uniquindio.habitx.controller;


import co.edu.uniquindio.habitx.model.Categoria;
import co.edu.uniquindio.habitx.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return new ResponseEntity<>(categoriaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Integer id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        return categoriaOptional.map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @Valid @RequestBody Categoria categoriaActualizada) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            categoriaActualizada.setIdCategoria(id);
            Categoria categoriaGuardada = categoriaRepository.save(categoriaActualizada);
            return new ResponseEntity<>(categoriaGuardada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}