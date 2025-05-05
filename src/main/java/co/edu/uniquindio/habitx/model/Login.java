package co.edu.uniquindio.habitx.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "Login")
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogin;

    private String contrasena;

    @OneToMany(mappedBy = "login")
    private List<Usuario> usuarios;  // Si quieres acceder a los usuarios desde Login
}
