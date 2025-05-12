package co.edu.uniquindio.habitx.repositories;


import co.edu.uniquindio.habitx.model.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Integer> {
}