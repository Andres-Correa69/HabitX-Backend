package co.edu.uniquindio.habitx;


import co.edu.uniquindio.habitx.controller.ObjetivoNutricionalController;
import co.edu.uniquindio.habitx.model.ObjetivoNutricional;
import co.edu.uniquindio.habitx.model.Usuario;
import co.edu.uniquindio.habitx.repositories.ObjetivoNutricionalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ObjetivoNutricionalControllerTest {

    @InjectMocks
    private ObjetivoNutricionalController objetivoNutricionalController;

    @Mock
    private ObjetivoNutricionalRepository objetivoNutricionalRepository;

    public ObjetivoNutricionalControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllObjetivosNutricionales_shouldReturnListOfObjetivos() {
        // Arrange
        ObjetivoNutricional objetivo1 = new ObjetivoNutricional();
        objetivo1.setIdObjetivoNutricional(1);
        objetivo1.setDescripcion("Bajar de peso");
        ObjetivoNutricional objetivo2 = new ObjetivoNutricional();
        objetivo2.setIdObjetivoNutricional(2);
        objetivo2.setDescripcion("Ganar masa muscular");
        List<ObjetivoNutricional> objetivos = Arrays.asList(objetivo1, objetivo2);

        when(objetivoNutricionalRepository.findAll()).thenReturn(objetivos);

        // Act
        List<ObjetivoNutricional> result = objetivoNutricionalController.getAllObjetivosNutricionales();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Bajar de peso", result.get(0).getDescripcion());
        assertEquals("Ganar masa muscular", result.get(1).getDescripcion());
        verify(objetivoNutricionalRepository, times(1)).findAll();
    }

    @Test
    void getObjetivoNutricionalById_existingId_shouldReturnObjetivo() {
        // Arrange
        int objetivoId = 1;
        ObjetivoNutricional objetivo = new ObjetivoNutricional();
        objetivo.setIdObjetivoNutricional(objetivoId);
        objetivo.setDescripcion("Bajar de peso");

        when(objetivoNutricionalRepository.findById(objetivoId)).thenReturn(Optional.of(objetivo));

        // Act
        ObjetivoNutricional result = objetivoNutricionalController.getObjetivoNutricionalById(objetivoId);

        // Assert
        assertEquals("Bajar de peso", result.getDescripcion());
        verify(objetivoNutricionalRepository, times(1)).findById(objetivoId);
    }

    @Test
    void getObjetivoNutricionalById_nonExistingId_shouldReturnNull() {
        // Arrange
        int objetivoId = 1;
        when(objetivoNutricionalRepository.findById(objetivoId)).thenReturn(Optional.empty());

        // Act
        ObjetivoNutricional result = objetivoNutricionalController.getObjetivoNutricionalById(objetivoId);

        // Assert
        assertNull(result);
        verify(objetivoNutricionalRepository, times(1)).findById(objetivoId);
    }

    @Test
    void getObjetivosNutricionalesByUsuarioId_existingUserId_shouldReturnListOfObjetivos() {
        // Arrange
        int usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        ObjetivoNutricional objetivo1 = new ObjetivoNutricional();
        objetivo1.setIdObjetivoNutricional(1);
        objetivo1.setDescripcion("Bajar de peso");
        objetivo1.setUsuario(usuario);
        ObjetivoNutricional objetivo2 = new ObjetivoNutricional();
        objetivo2.setIdObjetivoNutricional(2);
        objetivo2.setDescripcion("Mantener peso");
        objetivo2.setUsuario(usuario);
        List<ObjetivoNutricional> objetivos = Arrays.asList(objetivo1, objetivo2);

        when(objetivoNutricionalRepository.findByUsuarioIdUsuario(usuarioId)).thenReturn(objetivos);

        // Act
        List<ObjetivoNutricional> result = objetivoNutricionalController.getObjetivosNutricionalesByUsuarioId(usuarioId);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Bajar de peso", result.get(0).getDescripcion());
        assertEquals("Mantener peso", result.get(1).getDescripcion());
        assertEquals(usuarioId, result.get(0).getUsuario().getIdUsuario());
        assertEquals(usuarioId, result.get(1).getUsuario().getIdUsuario());
        verify(objetivoNutricionalRepository, times(1)).findByUsuarioIdUsuario(usuarioId);
    }

    @Test
    void getObjetivosNutricionalesByUsuarioId_nonExistingUserId_shouldReturnEmptyList() {
        // Arrange
        int usuarioId = 1;
        when(objetivoNutricionalRepository.findByUsuarioIdUsuario(usuarioId)).thenReturn(new ArrayList<>());

        // Act
        List<ObjetivoNutricional> result = objetivoNutricionalController.getObjetivosNutricionalesByUsuarioId(usuarioId);

        // Assert
        assertEquals(0, result.size());
        verify(objetivoNutricionalRepository, times(1)).findByUsuarioIdUsuario(usuarioId);
    }

    @Test
    void createObjetivoNutricional_shouldSaveAndReturnObjetivo() {
        // Arrange
        ObjetivoNutricional objetivoToCreate = new ObjetivoNutricional();
        objetivoToCreate.setDescripcion("Comer más saludable");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        objetivoToCreate.setUsuario(usuario);
        ObjetivoNutricional savedObjetivo = new ObjetivoNutricional();
        savedObjetivo.setIdObjetivoNutricional(3);
        savedObjetivo.setDescripcion("Comer más saludable");
        savedObjetivo.setUsuario(usuario);

        when(objetivoNutricionalRepository.save(objetivoToCreate)).thenReturn(savedObjetivo);

        // Act
        ObjetivoNutricional result = objetivoNutricionalController.createObjetivoNutricional(objetivoToCreate);

        // Assert
        assertEquals(3, result.getIdObjetivoNutricional());
        assertEquals("Comer más saludable", result.getDescripcion());
        assertEquals(1, result.getUsuario().getIdUsuario());
        verify(objetivoNutricionalRepository, times(1)).save(objetivoToCreate);
    }

    @Test
    void updateObjetivoNutricional_existingId_shouldUpdateAndReturnObjetivo() {
        // Arrange
        int objetivoId = 1;
        ObjetivoNutricional existingObjetivo = new ObjetivoNutricional();
        existingObjetivo.setIdObjetivoNutricional(objetivoId);
        existingObjetivo.setDescripcion("Bajar de peso");
        ObjetivoNutricional updatedObjetivoDetails = new ObjetivoNutricional();
        updatedObjetivoDetails.setDescripcion("Bajar de peso (meta alcanzada)");

        when(objetivoNutricionalRepository.findById(objetivoId)).thenReturn(Optional.of(existingObjetivo));
        when(objetivoNutricionalRepository.save(any(ObjetivoNutricional.class))).thenReturn(existingObjetivo);

        // Act
        ObjetivoNutricional result = objetivoNutricionalController.updateObjetivoNutricional(objetivoId, updatedObjetivoDetails);

        // Assert
        assertEquals("Bajar de peso (meta alcanzada)", result.getDescripcion());
        verify(objetivoNutricionalRepository, times(1)).findById(objetivoId);
        verify(objetivoNutricionalRepository, times(1)).save(any(ObjetivoNutricional.class));
    }

    @Test
    void updateObjetivoNutricional_nonExistingId_shouldReturnNull() {
        // Arrange
        int objetivoId = 1;
        ObjetivoNutricional updatedObjetivoDetails = new ObjetivoNutricional();
        updatedObjetivoDetails.setDescripcion("Bajar de peso (meta alcanzada)");

        when(objetivoNutricionalRepository.findById(objetivoId)).thenReturn(Optional.empty());

        // Act
        ObjetivoNutricional result = objetivoNutricionalController.updateObjetivoNutricional(objetivoId, updatedObjetivoDetails);

        // Assert
        assertNull(result);
        verify(objetivoNutricionalRepository, times(1)).findById(objetivoId);
        verify(objetivoNutricionalRepository, never()).save(any(ObjetivoNutricional.class));
    }

    @Test
    void deleteObjetivoNutricional_existingId_shouldDeleteObjetivo() {
        // Arrange
        int objetivoId = 1;
        doNothing().when(objetivoNutricionalRepository).deleteById(objetivoId);

        // Act
        objetivoNutricionalController.deleteObjetivoNutricional(objetivoId);

        // Assert
        verify(objetivoNutricionalRepository, times(1)).deleteById(objetivoId);
    }
}