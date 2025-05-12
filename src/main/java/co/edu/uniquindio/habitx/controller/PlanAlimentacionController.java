package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.*;
import co.edu.uniquindio.habitx.repositories.DetalleAlimentacionRepository;
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

    @Autowired
    private DetalleAlimentacionRepository detalleAlimentacionRepository;

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
            @Valid @RequestBody PlanAlimentacion nuevoPlanAlimentacion,
            @RequestParam(value = "idDetalleAlimentacion", required = false) Integer idDetalleAlimentacion) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            ObjetivoNutricional objetivoDelUsuario = usuario.getObjetivo();

            if (objetivoDelUsuario != null) {
                nuevoPlanAlimentacion.setObjetivoNutricional(objetivoDelUsuario);
                if (idDetalleAlimentacion != null) {
                    Optional<DetalleAlimentacion> detalleAlimentacionOptional = detalleAlimentacionRepository.findById(idDetalleAlimentacion);
                    detalleAlimentacionOptional.ifPresent(nuevoPlanAlimentacion::setDetalleAlimentacion);
                }
                PlanAlimentacion planGuardado = planAlimentacionRepository.save(nuevoPlanAlimentacion);
                return new ResponseEntity<>(planGuardado, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // El usuario no tiene un objetivo definido
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Usuario no encontrado
        }
    }

    // Nuevo endpoint para actualizar un plan de alimentación específico dentro de un objetivo
    @PutMapping("/{idObjetivo}/{idPlan}")
    public ResponseEntity<PlanAlimentacion> actualizarPlanAlimentacionDeObjetivo(
            @PathVariable Integer idObjetivo,
            @PathVariable Integer idPlan,
            @Valid @RequestBody PlanAlimentacion planActualizado) {
        Optional<ObjetivoNutricional> objetivoOptional = objetivoNutricionalRepository.findById(idObjetivo);
        Optional<PlanAlimentacion> planOptional = planAlimentacionRepository.findById(idPlan);

        if (objetivoOptional.isPresent() && planOptional.isPresent()) {
            PlanAlimentacion planExistente = planOptional.get();
            if (planExistente.getObjetivoNutricional().getIdObjetivoNutricional().equals(idObjetivo)) {
                planExistente.setNombre(planActualizado.getNombre());
                planExistente.setFechaInicio(planActualizado.getFechaInicio());
                PlanAlimentacion planGuardado = planAlimentacionRepository.save(planExistente);
                return new ResponseEntity<>(planGuardado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // El plan no pertenece a este objetivo
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Objetivo o Plan no encontrado
        }
    }

    // Nuevo endpoint para eliminar un plan de alimentación específico dentro de un objetivo
    @DeleteMapping("/{idObjetivo}/{idPlan}")
    public ResponseEntity<Void> eliminarPlanAlimentacionDeObjetivo(
            @PathVariable Integer idObjetivo,
            @PathVariable Integer idPlan) {
        Optional<ObjetivoNutricional> objetivoOptional = objetivoNutricionalRepository.findById(idObjetivo);
        Optional<PlanAlimentacion> planOptional = planAlimentacionRepository.findById(idPlan);

        if (objetivoOptional.isPresent() && planOptional.isPresent()) {
            PlanAlimentacion planExistente = planOptional.get();
            if (planExistente.getObjetivoNutricional().getIdObjetivoNutricional().equals(idObjetivo)) {
                planAlimentacionRepository.deleteById(idPlan);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Eliminación exitosa
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // El plan no pertenece a este objetivo
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Objetivo o Plan no encontrado
        }
    }

    // (Opcional) Endpoint para obtener todos los planes de un objetivo
    @GetMapping("/{idObjetivo}")
    public ResponseEntity<List<PlanAlimentacion>> obtenerPlanesDeObjetivo(@PathVariable Integer idObjetivo) {
        Optional<ObjetivoNutricional> objetivoOptional = objetivoNutricionalRepository.findById(idObjetivo);
        return objetivoOptional.map(objetivo -> new ResponseEntity<>(objetivo.getPlanAlimentacion(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
