package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import co.edu.uniquindio.habitx.repositories.PlanAlimentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlanAlimentacionController {

    @Autowired
    private PlanAlimentacionRepository planAlimentacionRepository;

    @GetMapping
    public List<PlanAlimentacion> getAllPlanesAlimentacion() {
        return planAlimentacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public PlanAlimentacion getPlanAlimentacionById(@PathVariable Integer id) {
        return planAlimentacionRepository.findById(id).orElse(null);
    }

    @GetMapping("/objetivos/{objetivoId}")
    public List<PlanAlimentacion> getPlanesAlimentacionByObjetivoId(@PathVariable Integer objetivoId) {
        return planAlimentacionRepository.findByObjetivoNutricionalIdObjetivoNutricional(objetivoId);
    }

    @PostMapping
    public PlanAlimentacion createPlanAlimentacion(@Valid @RequestBody PlanAlimentacion planAlimentacion) {
        return planAlimentacionRepository.save(planAlimentacion);
    }

    @PutMapping("/{id}")
    public PlanAlimentacion updatePlanAlimentacion(@PathVariable Integer id, @Valid @RequestBody PlanAlimentacion planAlimentacion) {
        PlanAlimentacion existingPlan = planAlimentacionRepository.findById(id).orElse(null);
        if (existingPlan != null) {
            existingPlan.setNombre(planAlimentacion.getNombre());
            existingPlan.setFechaInicio(planAlimentacion.getFechaInicio());
            return planAlimentacionRepository.save(existingPlan);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePlanAlimentacion(@PathVariable Integer id) {
        planAlimentacionRepository.deleteById(id);
    }
}