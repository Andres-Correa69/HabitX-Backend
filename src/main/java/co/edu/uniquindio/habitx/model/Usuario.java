package co.edu.uniquindio.habitx.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "idLogin")
    @JsonManagedReference
    private Login login;

    @OneToOne
    @JoinColumn(name = "id_perfil_nutricional")
    @JsonManagedReference
    private PerfilNutricional perfilNutricional;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private ObjetivoNutricional objetivo; // Cambia List a un solo ObjetivoNutricional

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "Usuario_ArticuloNutricional",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idArticuloNutricional")
    )
    private List<ArticuloNutricional> articulosNutricionales; // <<--- AÑADIMOS ESTO
}

