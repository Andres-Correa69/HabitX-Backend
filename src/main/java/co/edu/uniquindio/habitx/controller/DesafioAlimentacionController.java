package co.edu.uniquindio.habitx.controller;



import co.edu.uniquindio.habitx.model.DesafioAlimentacion;
import co.edu.uniquindio.habitx.model.CuentaUsuario;
import co.edu.uniquindio.habitx.model.NivelCuenta;
import co.edu.uniquindio.habitx.model.Usuario;
import co.edu.uniquindio.habitx.repositories.DesafioAlimentacionRepository;
import co.edu.uniquindio.habitx.repositories.CuentaUsuarioRepository;
import co.edu.uniquindio.habitx.repositories.NivelCuentaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/desafios")
public class DesafioAlimentacionController {

    @Autowired
    private DesafioAlimentacionRepository desafioAlimentacionRepository;

    @Autowired
    private CuentaUsuarioRepository cuentaUsuarioRepository;

    @Autowired
    private NivelCuentaRepository nivelCuentaRepository;

    @GetMapping
    public ResponseEntity<List<DesafioAlimentacion>> listarDesafios() {
        return new ResponseEntity<>(desafioAlimentacionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sql/getdesafios/recordatorios")
    public List<DesafioAlimentacion>  obtenerDesafiosRecordatoriosSQL(){

        return desafioAlimentacionRepository.obtenerDesafiosRecordatoriosSQL();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DesafioAlimentacion> obtenerDesafio(@PathVariable Integer id) {
        Optional<DesafioAlimentacion> desafioOptional = desafioAlimentacionRepository.findById(id);
        return desafioOptional.map(desafio -> new ResponseEntity<>(desafio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DesafioAlimentacion> crearDesafio(@Valid @RequestBody DesafioAlimentacion desafio) {
        DesafioAlimentacion nuevoDesafio = desafioAlimentacionRepository.save(desafio);
        return new ResponseEntity<>(nuevoDesafio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesafioAlimentacion> actualizarDesafio(@PathVariable Integer id, @Valid @RequestBody DesafioAlimentacion desafioActualizado) {
        Optional<DesafioAlimentacion> desafioOptional = desafioAlimentacionRepository.findById(id);
        if (desafioOptional.isPresent()) {
            desafioActualizado.setIdDesafioAlimentacion(id);
            DesafioAlimentacion desafioGuardado = desafioAlimentacionRepository.save(desafioActualizado);
            return new ResponseEntity<>(desafioGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PutMapping("/{id}/completar")
    public ResponseEntity<DesafioAlimentacion> completarDesafio(@PathVariable Integer id) {
        Optional<DesafioAlimentacion> desafioOptional = desafioAlimentacionRepository.findById(id);
        if (desafioOptional.isPresent()) {
            DesafioAlimentacion desafio = desafioOptional.get();
            desafio.setEstado(true);
            desafioAlimentacionRepository.save(desafio);

            // Lógica para verificar y subir de nivel
            subirNivelSiEsNecesario(desafio.getPlanAlimentacion().getObjetivoNutricional().getUsuario().getIdUsuario());

            return new ResponseEntity<>(desafio, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void subirNivelSiEsNecesario(Integer idUsuario) {
        Optional<CuentaUsuario> cuentaUsuarioOptional = cuentaUsuarioRepository.findByUsuarioIdUsuario(idUsuario);
        if (cuentaUsuarioOptional.isPresent()) {
            CuentaUsuario cuentaUsuario = cuentaUsuarioOptional.get();
            NivelCuenta nivelActual = cuentaUsuario.getNivelCuenta();

            // Lógica muy básica para subir de nivel (siempre sube al siguiente)
            if (nivelActual != null) {
                Optional<NivelCuenta> siguienteNivelOptional = nivelCuentaRepository.findById(nivelActual.getIdNivelCuenta() + 1);
                siguienteNivelOptional.ifPresent(cuentaUsuario::setNivelCuenta);
                cuentaUsuarioRepository.save(cuentaUsuario);
            } else {
                // Si no hay nivel actual, podrías asignar un nivel inicial
                Optional<NivelCuenta> primerNivelOptional = nivelCuentaRepository.findById(1); // Asumiendo que el primer nivel tiene ID 1
                primerNivelOptional.ifPresent(cuentaUsuario::setNivelCuenta);
                cuentaUsuarioRepository.save(cuentaUsuario);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDesafio(@PathVariable Integer id) {
        if (desafioAlimentacionRepository.existsById(id)) {
            desafioAlimentacionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
