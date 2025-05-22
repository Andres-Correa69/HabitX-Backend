package co.edu.uniquindio.habitx.repositories;


import co.edu.uniquindio.habitx.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    //query simple3 obtener todas las recetas
    @Query(value = "SELECT * FROM receta;", nativeQuery = true)
    List<Receta> obtenerTodasLasRecetasSQL();
}