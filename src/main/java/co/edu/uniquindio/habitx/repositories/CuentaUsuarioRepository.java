package co.edu.uniquindio.habitx.repositories;



import co.edu.uniquindio.habitx.model.CuentaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuario, Integer> {

    Optional<CuentaUsuario> findByUsuarioIdUsuario(Integer idUsuario);
}