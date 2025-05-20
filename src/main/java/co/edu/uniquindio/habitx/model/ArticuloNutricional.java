package co.edu.uniquindio.habitx.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ArticuloNutricional")
@Data
public class ArticuloNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticuloNutricional;

    @NotBlank(message = "El nombre del artículo es obligatorio")
    @Size(max = 45, message = "El nombre del artículo no puede exceder los 45 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción del artículo es obligatoria")
    @Size(max = 255, message = "La descripción del artículo no puede exceder los 255 caracteres")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    @NotNull(message = "La categoría del artículo es obligatoria")
    private Categoria categoria;

    @ManyToMany(mappedBy = "articulosNutricionales")
    @JsonManagedReference
    private List<Usuario> usuarios; // Relación ManyToMany con Usuario

    @ElementCollection
    @CollectionTable(name = "DetalleArticulo", joinColumns = @JoinColumn(name = "idArticuloNutricional"))
    private List<DetalleArticulo> detallesAdicionales;
}