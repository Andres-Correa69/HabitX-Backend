package co.edu.uniquindio.habitx.model;


import jakarta.persistence.Embeddable;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
@Data
public class DetalleArticulo {

    @NotBlank(message = "El título del detalle es obligatorio")
    @Size(max = 300, message = "El título del detalle no puede exceder los 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción del detalle es obligatoria")
    @Size(max = 10000, message = "La descripción del detalle no puede exceder los 255 caracteres")
    private String descripcion;
}