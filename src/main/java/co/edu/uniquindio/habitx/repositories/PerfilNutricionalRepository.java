package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.PerfilNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilNutricionalRepository extends JpaRepository<PerfilNutricional, Integer> {
}
