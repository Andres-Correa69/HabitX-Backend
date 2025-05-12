package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.ArticuloNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloNutricionalRepository extends JpaRepository<ArticuloNutricional, Integer> {
}