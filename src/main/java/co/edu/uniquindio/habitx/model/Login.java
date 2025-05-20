package co.edu.uniquindio.habitx.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private List<Usuario> usuarios;  // Si quieres acceder a los usuarios desde Login
}
