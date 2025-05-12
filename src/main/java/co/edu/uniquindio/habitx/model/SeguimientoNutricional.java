package co.edu.uniquindio.habitx.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SeguimientoNutricional")
@Data
public class SeguimientoNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeguimientoNutricional;

    private LocalDateTime fechaInicio;

    @ManyToOne
    @JoinColumn(name = "idDetalleAlimentacion")
    private DetalleAlimentacion detalleAlimentacion;

    @OneToOne(mappedBy = "seguimientoNutricional") // <<--- Aquí está la relación con PerfilNutricional
    private PerfilNutricional perfilNutricional;

    @ManyToMany
    @JoinTable(
            name = "SeguimientoNutricional_Estado",
            joinColumns = @JoinColumn(name = "idSeguimientoNutricional"),
            inverseJoinColumns = @JoinColumn(name = "idEstado")
    )
    private List<Estado> estados;

    @ManyToMany
    @JoinTable(
            name = "SeguimientoNutricional_Recomendacion",
            joinColumns = @JoinColumn(name = "idSeguimientoNutricional"),
            inverseJoinColumns = @JoinColumn(name = "idRecomendacion")
    )
    private List<Recomendacion> recomendaciones;
}