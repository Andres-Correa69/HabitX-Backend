package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.PerfilNutricional;
import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilNutricionalRepository extends JpaRepository<PerfilNutricional, Integer> {
}
