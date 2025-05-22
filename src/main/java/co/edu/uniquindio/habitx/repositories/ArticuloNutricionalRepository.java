package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.ArticuloNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloNutricionalRepository extends JpaRepository<ArticuloNutricional, Integer> {

    //query simple2 obtener los articulos nutricionales
    @Query(value = "SELECT * FROM articulo_nutricional;", nativeQuery = true)
    List<ArticuloNutricional> obtenerArticulosSQL();

}