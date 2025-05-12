package co.edu.uniquindio.habitx.repositories;


import co.edu.uniquindio.habitx.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
}