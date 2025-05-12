package co.edu.uniquindio.habitx.controller;



import co.edu.uniquindio.habitx.model.Recompensa;
import co.edu.uniquindio.habitx.repositories.RecompensaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recompensas")
public class RecompensaController {

    @Autowired
    private RecompensaRepository recompensaRepository;
    @GetMapping
    public ResponseEntity<List<Recompensa>> listarRecompensas() {
        return new ResponseEntity<>(recompensaRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Recompensa> obtenerRecompensa(@PathVariable Integer id) {
        Optional<Recompensa> recompensaOptional = recompensaRepository.findById(id);
        return recompensaOptional.map(recompensa -> new ResponseEntity<>(recompensa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Recompensa> crearRecompensa(@Valid @RequestBody Recompensa recompensa) {
        Recompensa nuevaRecompensa = recompensaRepository.save(recompensa);
        return new ResponseEntity<>(nuevaRecompensa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recompensa> actualizarRecompensa(@PathVariable Integer id, @Valid @RequestBody Recompensa recompensaActualizada) {
        Optional<Recompensa> recompensaOptional = recompensaRepository.findById(id);
        if (recompensaOptional.isPresent()) {
            recompensaActualizada.setIdRecompensa(id);
            Recompensa recompensaGuardada = recompensaRepository.save(recompensaActualizada);
            return new ResponseEntity<>(recompensaGuardada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecompensa(@PathVariable Integer id) {
        if (recompensaRepository.existsById(id)) {
            recompensaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}