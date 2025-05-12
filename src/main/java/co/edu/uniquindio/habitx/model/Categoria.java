package co.edu.uniquindio.habitx.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Categoria")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 45, message = "El nombre de la categoría no puede exceder los 45 caracteres")
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<ArticuloNutricional> articulosNutricionales;
}