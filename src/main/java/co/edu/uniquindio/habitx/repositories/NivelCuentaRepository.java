package co.edu.uniquindio.habitx.repositories;


import co.edu.uniquindio.habitx.model.NivelCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelCuentaRepository extends JpaRepository<NivelCuenta, Integer> {
}