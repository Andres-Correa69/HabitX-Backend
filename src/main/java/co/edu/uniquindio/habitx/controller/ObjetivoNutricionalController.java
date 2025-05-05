package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.ObjetivoNutricional;
import co.edu.uniquindio.habitx.repositories.ObjetivoNutricionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/objetivos")
public class ObjetivoNutricionalController {

    @Autowired
    private ObjetivoNutricionalRepository objetivoNutricionalRepository;

    @GetMapping
    public List<ObjetivoNutricional> getAllObjetivosNutricionales() {
        return objetivoNutricionalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ObjetivoNutricional getObjetivoNutricionalById(@PathVariable Integer id) {
        return objetivoNutricionalRepository.findById(id).orElse(null);
    }

    @GetMapping("/usuarios/{usuarioId}")
    public List<ObjetivoNutricional> getObjetivosNutricionalesByUsuarioId(@PathVariable Integer usuarioId) {
        return objetivoNutricionalRepository.findByUsuarioIdUsuario(usuarioId);
    }

    @PostMapping
    public ObjetivoNutricional createObjetivoNutricional(@Valid @RequestBody ObjetivoNutricional objetivoNutricional) {
        return objetivoNutricionalRepository.save(objetivoNutricional);
    }

    @PutMapping("/{id}")
    public ObjetivoNutricional updateObjetivoNutricional(@PathVariable Integer id, @Valid @RequestBody ObjetivoNutricional objetivoNutricional) {
        ObjetivoNutricional existingObjetivo = objetivoNutricionalRepository.findById(id).orElse(null);
        if (existingObjetivo != null) {
            existingObjetivo.setDescripcion(objetivoNutricional.getDescripcion());
            return objetivoNutricionalRepository.save(existingObjetivo);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteObjetivoNutricional(@PathVariable Integer id) {
        objetivoNutricionalRepository.deleteById(id);
    }
}
