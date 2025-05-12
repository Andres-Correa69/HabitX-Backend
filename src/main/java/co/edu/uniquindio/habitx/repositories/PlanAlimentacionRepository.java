package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanAlimentacionRepository extends JpaRepository<PlanAlimentacion, Integer> {
    List<PlanAlimentacion> findByObjetivoNutricionalIdObjetivoNutricional(Integer objetivoNutricionalId);
    List<PlanAlimentacion> findByObjetivoNutricional_Usuario_IdUsuario(Integer usuarioId);
    Optional<PlanAlimentacion> findById(Integer id);
}