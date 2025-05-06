package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.PerfilNutricional;
import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import co.edu.uniquindio.habitx.repositories.PerfilNutricionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilNutricionalController {

    @Autowired
    private PerfilNutricionalRepository perfilNutricionalRepository;

    @GetMapping
    public List<PerfilNutricional> getAllPerfilesNutricionales() {
        return perfilNutricionalRepository.findAll();
    }
    @GetMapping("/{id}")
    public PerfilNutricional getPerfilNutricionalById(@PathVariable Integer id) {
        return perfilNutricionalRepository.findById(id).orElse(null);
    }


    @PostMapping // Nuevo endpoint para crear un PerfilNutricional
    public PerfilNutricional createPerfilNutricional(@Valid @RequestBody PerfilNutricional perfilNutricional) {
        return perfilNutricionalRepository.save(perfilNutricional);
    }

    @PutMapping("/{id}")
    public PerfilNutricional updatePerfilNutricional(@PathVariable Integer id, @Valid @RequestBody PerfilNutricional perfilNutricional) {
        PerfilNutricional existingPerfil = perfilNutricionalRepository.findById(id).orElse(null);
        if (existingPerfil != null) {
            existingPerfil.setPeso(perfilNutricional.getPeso());
            existingPerfil.setAltura(perfilNutricional.getAltura());
            existingPerfil.setImc(perfilNutricional.getImc());
            return perfilNutricionalRepository.save(existingPerfil);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePerfilNutricional(@PathVariable Integer id) {
        perfilNutricionalRepository.deleteById(id);
    }
}