package co.edu.uniquindio.habitx.controller;



import co.edu.uniquindio.habitx.model.Recordatorios;
import co.edu.uniquindio.habitx.repositories.RecordatoriosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recordatorios")
public class RecordatoriosController {

    @Autowired
    private RecordatoriosRepository recordatoriosRepository;

    @GetMapping
    public ResponseEntity<List<Recordatorios>> listarRecordatorios() {
        return new ResponseEntity<>(recordatoriosRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recordatorios> obtenerRecordatorio(@PathVariable Integer id) {
        Optional<Recordatorios> recordatorioOptional = recordatoriosRepository.findById(id);
        return recordatorioOptional.map(recordatorio -> new ResponseEntity<>(recordatorio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Recordatorios> crearRecordatorio(@Valid @RequestBody Recordatorios recordatorio) {
        Recordatorios nuevoRecordatorio = recordatoriosRepository.save(recordatorio);
        return new ResponseEntity<>(nuevoRecordatorio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recordatorios> actualizarRecordatorio(@PathVariable Integer id, @Valid @RequestBody Recordatorios recordatorioActualizado) {
        Optional<Recordatorios> recordatorioOptional = recordatoriosRepository.findById(id);
        if (recordatorioOptional.isPresent()) {
            recordatorioActualizado.setIdRecordatorios(id);
            Recordatorios recordatorioGuardado = recordatoriosRepository.save(recordatorioActualizado);
            return new ResponseEntity<>(recordatorioGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecordatorio(@PathVariable Integer id) {
        if (recordatoriosRepository.existsById(id)) {
            recordatoriosRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}