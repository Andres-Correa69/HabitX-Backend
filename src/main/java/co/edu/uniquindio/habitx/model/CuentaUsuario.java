package co.edu.uniquindio.habitx.model;


import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "idRecompensa")
    private Recompensa recompensa;

    @ManyToOne
    @JoinColumn(name = "idNivelCuenta")
    private NivelCuenta nivelCuenta;
}