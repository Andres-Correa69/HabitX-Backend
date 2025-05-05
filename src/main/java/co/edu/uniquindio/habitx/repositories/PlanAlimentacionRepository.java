package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanAlimentacionRepository extends JpaRepository<PlanAlimentacion, Integer> {
    List<PlanAlimentacion> findByObjetivoNutricionalIdObjetivoNutricional(Integer objetivoNutricionalId);
}