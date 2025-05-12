package co.edu.uniquindio.habitx.repositories;



import co.edu.uniquindio.habitx.model.CuentaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuario, Integer> {
}