package co.edu.uniquindio.habitx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "DetalleAlimentacion")
@Data
public class DetalleAlimentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleAlimentacion;

    @NotNull(message = "El número de porciones es obligatorio")
    @Min(value = 1, message = "El número de porciones debe ser al menos 1")
    private Integer numeroPorciones;

    @ManyToOne // <<--- Corregimos el mappedBy
    @JsonBackReference(value = "detalleAlimentacion")
    private PlanAlimentacion planeAlimentacion;

    @OneToMany(mappedBy = "detalleAlimentacion")
    @JsonManagedReference
    private List<Receta> recetas;

    @OneToMany(mappedBy = "detalleAlimentacion")
    private List<SeguimientoNutricional> seguimientosNutricionales;
}