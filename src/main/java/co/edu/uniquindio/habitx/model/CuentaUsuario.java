package co.edu.uniquindio.habitx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "CuentaUsuario")
@Data
public class CuentaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuentaUsuario;

    @OneToOne
    @JoinColumn(name = "idUsuario", unique = true)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "CuentaUsuario_Recompensa",
            joinColumns = @JoinColumn(name = "idCuentaUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idRecompensa")
    )
    private List<Recompensa> recompensas;

    @ManyToOne
    @JoinColumn(name = "idNivelCuenta")
    private NivelCuenta nivelCuenta;
}