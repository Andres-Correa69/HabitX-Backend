package co.edu.uniquindio.habitx.repositories;

import co.edu.uniquindio.habitx.model.DesafioAlimentacion;
import co.edu.uniquindio.habitx.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByNombre(String nombre);

    List<Usuario> findByEmailContaining(String email);

    @Query(value = "SELECT * FROM usuario", nativeQuery = true)
    List<Usuario> obtenerTodosLosUsuariosSQL();

    @Query(value = "SELECT u.id_usuario, u.nombre, u.apellido, u.edad, p.altura, p.peso, p.imc FROM usuario u INNER JOIN perfil_nutricional p ON u.id_usuario = p.id_usuario;", nativeQuery = true)
    List<Usuario> obtenerTodosLosUsuariosSQLperfiles();

    @Query(value = "SELECT u.id_usuario, u.nombre, u.apellido, u.edad, u.email, u.id_genero, u.id_login, u.id_perfil_nutricional, g.descripcion FROM usuario u INNER JOIN genero g ON u.id_genero = g.id_genero WHERE g.descripcion = 'Masculino';", nativeQuery = true)
    List<Usuario> obtenerUsuariosMasculinosSQL();

    @Query(value = "SELECT u.id_usuario, u.edad, u.email, u.id_genero, u.id_login, u.id_perfil_nutricional, u.nombre, u.apellido, pn.imc FROM usuario u JOIN perfil_nutricional pn ON u.id_perfil_nutricional = pn.id_perfil_nutricional WHERE pn.imc > (   SELECT AVG(imc) FROM perfil_nutricional);", nativeQuery = true)
    List<Usuario> obtenerUsuariosMayorPromedioIMC();
}