package co.edu.uniquindio.habitx.model;



import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Recompensa")
@Data
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecompensa;

    @NotBlank(message = "La descripción de la recompensa es obligatoria")
    @Size(max = 45, message = "La descripción de la recompensa no puede exceder los 45 caracteres")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idNivelCuenta")
    private NivelCuenta nivelCuenta;

    @OneToMany(mappedBy = "recompensa")
    private List<CuentaUsuario> cuentasUsuario;
}