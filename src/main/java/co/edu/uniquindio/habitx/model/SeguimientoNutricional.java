package co.edu.uniquindio.habitx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private DetalleAlimentacion detalleAlimentacion;

    @OneToOne(mappedBy = "seguimientoNutricional")// <<--- Aquí está la relación con PerfilNutricional
    @JsonBackReference
    private PerfilNutricional perfilNutricional;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "SeguimientoNutricional_Estado",
            joinColumns = @JoinColumn(name = "idSeguimientoNutricional"),
            inverseJoinColumns = @JoinColumn(name = "idEstado")
    )
    private List<Estado> estados;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "SeguimientoNutricional_Recomendacion",
            joinColumns = @JoinColumn(name = "idSeguimientoNutricional"),
            inverseJoinColumns = @JoinColumn(name = "idRecomendacion")
    )
    private List<Recomendacion> recomendaciones;
}