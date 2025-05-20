package co.edu.uniquindio.habitx.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Genero")
@Data
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGenero;

    private String descripcion;


    @OneToMany(mappedBy = "genero")
    @JsonBackReference
    private List<Usuario> usuarios;  // Si quieres acceder a los usuarios desde Genero
}
