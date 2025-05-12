package co.edu.uniquindio.habitx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    @OneToOne
    @JoinColumn(name = "idUsuario", unique = true)
    @JsonBackReference
    private Usuario usuario;

    @OneToOne(mappedBy = "objetivoNutricional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private PlanAlimentacion planAlimentacion;
}
