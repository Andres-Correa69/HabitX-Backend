package co.edu.uniquindio.habitx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "CuentaUsuario_Recompensa",
            joinColumns = @JoinColumn(name = "idCuentaUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idRecompensa")
    )
    @JsonBackReference
    private List<Recompensa> recompensas;

    @ManyToOne
    @JoinColumn(name = "idNivelCuenta")
    @JsonBackReference
    private NivelCuenta nivelCuenta;
}