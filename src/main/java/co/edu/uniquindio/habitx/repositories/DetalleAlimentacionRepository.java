package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.DetalleAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleAlimentacionRepository extends JpaRepository<DetalleAlimentacion, Integer> {
}