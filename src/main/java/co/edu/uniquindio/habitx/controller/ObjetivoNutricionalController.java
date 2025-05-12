    package co.edu.uniquindio.habitx.controller;

    import co.edu.uniquindio.habitx.model.ObjetivoNutricional;
    import co.edu.uniquindio.habitx.model.Usuario;
    import co.edu.uniquindio.habitx.repositories.ObjetivoNutricionalRepository;
    import co.edu.uniquindio.habitx.repositories.UsuarioRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import jakarta.validation.Valid;
    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/objetivos")
    public class ObjetivoNutricionalController {

        @Autowired
        private ObjetivoNutricionalRepository objetivoNutricionalRepository;

        @Autowired
        private UsuarioRepository usuarioRepository; // Inyecta el UsuarioRepository

        // ... tus otros métodos ...
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

        @PutMapping("/{idObjetivo}/usuarios/{idUsuario}")
        public ResponseEntity<ObjetivoNutricional> asignarObjetivoAUsuario(
                @PathVariable Integer idObjetivo,
                @PathVariable Integer idUsuario) {
            Optional<ObjetivoNutricional> objetivoOptional = objetivoNutricionalRepository.findById(idObjetivo);
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

            if (objetivoOptional.isPresent() && usuarioOptional.isPresent()) {
                ObjetivoNutricional objetivo = objetivoOptional.get();
                Usuario usuario = usuarioOptional.get();
                objetivo.setUsuario(usuario);
                ObjetivoNutricional objetivoActualizado = objetivoNutricionalRepository.save(objetivo);
                return new ResponseEntity<>(objetivoActualizado, HttpStatus.OK);
            } else if (objetivoOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Objetivo no encontrado
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Usuario no encontrado
            }
        }
    }
