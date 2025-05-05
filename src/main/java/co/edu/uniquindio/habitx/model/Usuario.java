package co.edu.uniquindio.habitx.model;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Email;


@Entity
@Table(name = "Usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "idLogin")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "idPerfilNutricional")
    private PerfilNutricional perfilNutricional;

    @OneToMany(mappedBy = "usuario")
    private List<ObjetivoNutricional> objetivos;

}
