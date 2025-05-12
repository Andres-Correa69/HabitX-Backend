package co.edu.uniquindio.habitx.repositories;


import co.edu.uniquindio.habitx.model.Recordatorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordatoriosRepository extends JpaRepository<Recordatorios, Integer> {
}
