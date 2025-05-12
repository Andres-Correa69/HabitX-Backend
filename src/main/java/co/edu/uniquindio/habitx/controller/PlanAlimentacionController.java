package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.CuentaUsuario;
import co.edu.uniquindio.habitx.model.ObjetivoNutricional;
import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import co.edu.uniquindio.habitx.model.Usuario;
import co.edu.uniquindio.habitx.repositories.ObjetivoNutricionalRepository;
import co.edu.uniquindio.habitx.repositories.PlanAlimentacionRepository;
import co.edu.uniquindio.habitx.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planes")
public class PlanAlimentacionController {

    @Autowired
    private PlanAlimentacionRepository planAlimentacionRepository;

    @Autowired
    private ObjetivoNutricionalRepository objetivoNutricionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public List<PlanAlimentacion> getAllPlanesAlimentacion() {
        return planAlimentacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public PlanAlimentacion getPlanAlimentacionById(@PathVariable Integer id) {
        return planAlimentacionRepository.findById(id).orElse(null);
    }

    @GetMapping("/usuario/{idUsuario}") // Endpoint para buscar por idUsuario
    public ResponseEntity<List<PlanAlimentacion>> obtenerPlanesAlimentacionPorUsuario(@PathVariable Integer idUsuario) {
        List<PlanAlimentacion> planesAlimentacion = planAlimentacionRepository.findByObjetivoNutricional_Usuario_IdUsuario(idUsuario);
        if (planesAlimentacion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(planesAlimentacion, HttpStatus.OK);
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


    @PostMapping("/{idUsuario}/objetivos")
    public ResponseEntity<PlanAlimentacion> crearPlanAlimentacionParaUsuario(
            @PathVariable Integer idUsuario,
            @Valid @RequestBody PlanAlimentacion nuevoPlanAlimentacion) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            ObjetivoNutricional objetivoDelUsuario = usuario.getObjetivo();

            if (objetivoDelUsuario != null) {
                nuevoPlanAlimentacion.setObjetivoNutricional(objetivoDelUsuario);
                PlanAlimentacion planGuardado = planAlimentacionRepository.save(nuevoPlanAlimentacion);
                return new ResponseEntity<>(planGuardado, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // El usuario no tiene un objetivo definido
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Usuario no encontrado
        }
    }

    @PutMapping("/objetivos/{idObjetivo}")
    public ResponseEntity<PlanAlimentacion> actualizarPlanAlimentacionDeObjetivoUsuario(
            @PathVariable Integer idObjetivo,
            @Valid @RequestBody PlanAlimentacion planActualizado) {
        Optional<ObjetivoNutricional> objetivoOptional = objetivoNutricionalRepository.findById(idObjetivo);

        if (objetivoOptional.isPresent()) {
            ObjetivoNutricional objetivo = objetivoOptional.get();
            PlanAlimentacion planExistente = objetivo.getPlanAlimentacion();

            if (planExistente != null) {
                planExistente.setNombre(planActualizado.getNombre());
                planExistente.setFechaInicio(planActualizado.getFechaInicio());
                PlanAlimentacion planGuardado = planAlimentacionRepository.save(planExistente);
                return new ResponseEntity<>(planGuardado, HttpStatus.OK);
            } else {
                // El objetivo no tiene un plan asociado, podrías crearlo aquí si lo deseas
                planActualizado.setObjetivoNutricional(objetivo);
                PlanAlimentacion planCreado = planAlimentacionRepository.save(planActualizado);
                return new ResponseEntity<>(planCreado, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Objetivo no encontrado
        }
    }

    @DeleteMapping("/objetivos/{idObjetivo}")
    public ResponseEntity<Void> eliminarPlanAlimentacionDeObjetivoUsuario(
            @PathVariable Integer idObjetivo) {
        Optional<ObjetivoNutricional> objetivoOptional = objetivoNutricionalRepository.findById(idObjetivo);

        if (objetivoOptional.isPresent()) {
            ObjetivoNutricional objetivo = objetivoOptional.get();
            PlanAlimentacion planExistente = objetivo.getPlanAlimentacion();

            if (planExistente != null) {
                planAlimentacionRepository.delete(planExistente);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Eliminación exitosa
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // El objetivo no tiene un plan asociado
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Objetivo no encontrado
        }
    }
}