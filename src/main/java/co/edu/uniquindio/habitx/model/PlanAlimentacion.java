package co.edu.uniquindio.habitx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PlanAlimentacion")
@Data
public class PlanAlimentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlanAlimentacion;

    @ManyToOne
    @JoinColumn(name = "idObjetivoNutricional", unique = true)
    @JsonBackReference
    private ObjetivoNutricional objetivoNutricional; // Nombre consistente

    @NotBlank(message = "El nombre del plan es obligatorio")
    @Size(max = 555, message = "El nombre del plan no puede exceder los 45 caracteres")
    private String nombre;

    private LocalDateTime fechaInicio;

    @ManyToOne
    @JoinColumn(name = "idDetalleAlimentacion")
    @JsonManagedReference(value = "detalleAlimentacion")
    private DetalleAlimentacion detalleAlimentacion;

    @OneToMany(mappedBy = "planAlimentacion")
    private List<DesafioAlimentacion> desafiosAlimentacion;
}