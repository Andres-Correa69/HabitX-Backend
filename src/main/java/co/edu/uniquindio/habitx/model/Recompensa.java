package co.edu.uniquindio.habitx.model;


import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

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
    private NivelCuenta nivelCuenta;

    @OneToMany(mappedBy = "recompensa")
    private List<CuentaUsuario> cuentasUsuario;

    @Column(nullable = false) // Indica que el campo no puede ser nulo en la base de datos
    private Boolean activo;

    // Puedes agregar una anotación para establecer un valor por defecto si lo deseas
    // @ColumnDefault("true")
    // private Boolean activo;
}