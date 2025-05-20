package co.edu.uniquindio.habitx.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import jakarta.validation.*;

@Entity
@Table(name = "PerfilNutricional")
@Data
public class PerfilNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfilNutricional;

    @Min(value = 0, message = "El peso no puede ser negativo")
    private Float peso;

    @Min(value = 0, message = "La altura no puede ser negativa")
    private Float altura;

    private Float imc;

    //La relacion puede ser @OneToOne o @ManyToOne, dependiendo de la cardinalidad
    @OneToOne // <<--- Relación con SeguimientoNutricional
    @JoinColumn(name = "idSeguimientoNutricional")
    @JsonManagedReference
    private SeguimientoNutricional seguimientoNutricional;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

}