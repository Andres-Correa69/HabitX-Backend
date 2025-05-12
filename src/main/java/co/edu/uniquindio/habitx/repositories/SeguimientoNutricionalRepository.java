package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.SeguimientoNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoNutricionalRepository extends JpaRepository<SeguimientoNutricional, Integer> {
}