package co.edu.uniquindio.habitx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Receta")
@Data
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    @NotBlank(message = "El nombre de la receta es obligatorio")
    @Size(max = 250, message = "El nombre de la receta no puede exceder los 45 caracteres")
    private String nombre;

    @NotBlank(message = "Los ingredientes de la receta son obligatorios")
    @Size(max = 1500, message = "Los ingredientes de la receta no pueden exceder los 45 caracteres")
    private String ingredientes; // Definido como VARCHAR(45) según tu tabla

    @Size(max = 100, message = "Las calorías de la receta no pueden exceder los 45 caracteres")
    private String calorias; // Añadido el campo calorias según tu tabla

    @ManyToOne
    @JoinColumn(name = "idDetalleAlimentacion")
    @JsonBackReference
    private DetalleAlimentacion detalleAlimentacion;
}
