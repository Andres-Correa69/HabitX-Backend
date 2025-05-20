package co.edu.uniquindio.habitx.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "NivelCuenta")
@Data
public class NivelCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNivelCuenta;

    @NotBlank(message = "El nivel es obligatorio")
    @Size(max = 45, message = "El nivel no puede exceder los 45 caracteres")
    private String nivel;

    @OneToMany(mappedBy = "nivelCuenta")
    @JsonManagedReference
    private List<CuentaUsuario> cuentasUsuario;

    @OneToMany(mappedBy = "nivelCuenta")
    @JsonBackReference
    private List<Recompensa> recompensas;
}