package co.edu.uniquindio.habitx.model;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "DesafioAlimentacion")
@Data
public class DesafioAlimentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDesafioAlimentacion;

    @NotBlank(message = "La descripción del desafío es obligatoria")
    @Size(max = 1000, message = "La descripción del desafío no puede exceder los 45 caracteres")
    private String descripcion;

    @NotNull(message = "El estado del desafío es obligatorio")
    private Boolean estado; // TINYINT en la base de datos (0 o 1)

    @ManyToOne
    @JoinColumn(name = "idPlanAlimentacion")
    private PlanAlimentacion planAlimentacion;

    @OneToMany(mappedBy = "desafioAlimentacion")
    private List<Recordatorios> recordatorios;
}