package co.edu.uniquindio.habitx.repositories;


import co.edu.uniquindio.habitx.model.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Integer> {
}