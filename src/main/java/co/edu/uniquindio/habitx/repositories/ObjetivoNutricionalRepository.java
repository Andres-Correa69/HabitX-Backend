package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.ObjetivoNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObjetivoNutricionalRepository extends JpaRepository<ObjetivoNutricional, Integer> {
    List<ObjetivoNutricional> findByUsuarioIdUsuario(Integer usuarioId);
}
