package co.edu.uniquindio.habitx.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Estado")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @NotBlank(message = "La descripción del estado es obligatoria")
    @Size(max = 1000, message = "La descripción del estado no puede exceder los 1000 caracteres")
    private String descripcion;

    @ManyToMany(mappedBy = "estados")
    @JsonManagedReference
    private List<SeguimientoNutricional> seguimientosNutricionales;
}