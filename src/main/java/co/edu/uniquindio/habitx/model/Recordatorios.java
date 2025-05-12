package co.edu.uniquindio.habitx.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Recordatorios")
@Data
public class Recordatorios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecordatorios;

    @NotBlank(message = "La descripción del recordatorio es obligatoria")
    @Size(max = 255, message = "La descripción del recordatorio no puede exceder los 45 caracteres")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idDesafioAlimentacion")
    private DesafioAlimentacion desafioAlimentacion;
}
