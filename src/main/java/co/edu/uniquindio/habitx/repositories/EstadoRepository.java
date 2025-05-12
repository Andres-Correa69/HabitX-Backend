package co.edu.uniquindio.habitx.repositories;


import co.edu.uniquindio.habitx.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}