package co.edu.uniquindio.habitx.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;

@Entity
@Table(name = "PlanAlimentacion")
@Data
public class PlanAlimentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlanAlimentacion;

    @NotBlank(message = "El nombre del plan es obligatorio")
    private String nombre;

    private LocalDateTime fechaInicio;

    @ManyToOne
    @JoinColumn(name = "idObjetivoNutricional")
    private ObjetivoNutricional objetivoNutricional;

}