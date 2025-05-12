package co.edu.uniquindio.habitx.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne // Cambia ManyToOne a OneToOne
    @JoinColumn(name = "idUsuario", unique = true) // Asegura que solo un usuario esté asociado
    @JsonBackReference
    private Usuario usuario;

}