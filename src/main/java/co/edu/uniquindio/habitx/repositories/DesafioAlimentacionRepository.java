package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.DesafioAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesafioAlimentacionRepository extends JpaRepository<DesafioAlimentacion, Integer> {

    @Query(value = "SELECT de.id_desafio_alimentacion, de.descripcion, re.descripcion FROM desafio_alimentacion de JOIN recordatorios re ON de.id_desafio_alimentacion = re.id_desafio_alimentacion;", nativeQuery = true)
    List<DesafioAlimentacion> obtenerDesafiosRecordatoriosSQL();
}