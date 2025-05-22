package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanAlimentacionRepository extends JpaRepository<PlanAlimentacion, Integer> {
    List<PlanAlimentacion> findByObjetivoNutricionalIdObjetivoNutricional(Integer objetivoNutricionalId);
    List<PlanAlimentacion> findByObjetivoNutricional_Usuario_IdUsuario(Integer usuarioId);
    Optional<PlanAlimentacion> findById(Integer id);

    //query intermedia4 obtener los planes y sus desafios
    @Query(value = "SELECT DISTINCT pl.nombre, pl.id_plan_alimentacion, pl.id_detalle_alimentacion, pl.fecha_inicio, pl.id_objetivo_nutricional, de.descripcion FROM plan_alimentacion pl  JOIN desafio_alimentacion de ON pl.id_plan_alimentacion = de.id_plan_alimentacion;", nativeQuery = true)
    List<PlanAlimentacion> obtenerPlanesyDesafiosSQL();

    //query avanzada3 obtener el planes de alimentacion los cuales sus recetas tengan mas de 500 calorias
    @Query(value = "SELECT DISTINCT pl.nombre, pl.id_plan_alimentacion, pl.id_detalle_alimentacion, pl.fecha_inicio, pl.id_objetivo_nutricional, r.nombre AS nombre_receta,  r.calorias FROM plan_alimentacion pl JOIN receta r ON pl.id_detalle_alimentacion = r.id_detalle_alimentacion WHERE CAST(r.calorias AS UNSIGNED) > 500;", nativeQuery = true)
    List<PlanAlimentacion> obtenerRecetasCaloriasSQL();
}