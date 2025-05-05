package co.edu.uniquindio.habitx.model;



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

//    @ManyToOne
//    @JoinColumn(name = "idSeguimientoNutricional")
//    private SeguimientoNutricional seguimientoNutricional;

}