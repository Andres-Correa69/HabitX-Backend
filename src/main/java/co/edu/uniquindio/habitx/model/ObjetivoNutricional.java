package co.edu.uniquindio.habitx.model;


import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "ObjetivoNutricional")
@Data
public class ObjetivoNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObjetivoNutricional;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

}