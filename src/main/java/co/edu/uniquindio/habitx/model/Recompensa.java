package co.edu.uniquindio.habitx.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Size(max = 500, message = "La descripción de la recompensa no puede exceder los 500 caracteres")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idNivelCuenta")
    @JsonManagedReference
    private NivelCuenta nivelCuenta;

    @ManyToMany(mappedBy = "recompensas")
    @JsonManagedReference
    private List<CuentaUsuario> cuentasUsuario;

    @Column(nullable = false)
    private Boolean activo;
}