package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.DesafioAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesafioAlimentacionRepository extends JpaRepository<DesafioAlimentacion, Integer> {
}