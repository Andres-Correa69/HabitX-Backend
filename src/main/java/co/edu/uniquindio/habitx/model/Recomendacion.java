package co.edu.uniquindio.habitx.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Recomendacion")
@Data
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecomendacion;

    @NotBlank(message = "La descripción de la recomendación es obligatoria")
    @Size(max = 1000, message = "La descripción de la recomendación no puede exceder los 45 caracteres")
    private String descripcion;

    private LocalDateTime fecha;

    @ManyToMany(mappedBy = "recomendaciones")
    private List<SeguimientoNutricional> seguimientosNutricionales;
}