package co.edu.uniquindio.habitx;


import co.edu.uniquindio.habitx.controller.PerfilNutricionalController;
import co.edu.uniquindio.habitx.model.PerfilNutricional;
import co.edu.uniquindio.habitx.repositories.PerfilNutricionalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PerfilNutricionalControllerTest {

    @InjectMocks
    private PerfilNutricionalController perfilNutricionalController;

    @Mock
    private PerfilNutricionalRepository perfilNutricionalRepository;

    public PerfilNutricionalControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPerfilNutricionalById_existingId_shouldReturnPerfilNutricional() {
        // Arrange
        int perfilId = 1;
        PerfilNutricional perfil = new PerfilNutricional();
        perfil.setIdPerfilNutricional(perfilId);
        perfil.setPeso(70.5f);

        when(perfilNutricionalRepository.findById(perfilId)).thenReturn(Optional.of(perfil));

        // Act
        PerfilNutricional result = perfilNutricionalController.getPerfilNutricionalById(perfilId);

        // Assert
        assertEquals(70.5f, result.getPeso());
        verify(perfilNutricionalRepository, times(1)).findById(perfilId);
    }

    @Test
    void getPerfilNutricionalById_nonExistingId_shouldReturnNull() {
        // Arrange
        int perfilId = 1;
        when(perfilNutricionalRepository.findById(perfilId)).thenReturn(Optional.empty());

        // Act
        PerfilNutricional result = perfilNutricionalController.getPerfilNutricionalById(perfilId);

        // Assert
        assertNull(result);
        verify(perfilNutricionalRepository, times(1)).findById(perfilId);
    }

    @Test
    void createPerfilNutricional_shouldSaveAndReturnPerfilNutricional() {
        // Arrange
        PerfilNutricional perfilToCreate = new PerfilNutricional();
        perfilToCreate.setPeso(65.0f);
        perfilToCreate.setAltura(1.70f);
        PerfilNutricional savedPerfil = new PerfilNutricional();
        savedPerfil.setIdPerfilNutricional(1);
        savedPerfil.setPeso(65.0f);
        savedPerfil.setAltura(1.70f);

        when(perfilNutricionalRepository.save(perfilToCreate)).thenReturn(savedPerfil);

        // Act
        PerfilNutricional result = perfilNutricionalController.createPerfilNutricional(perfilToCreate);

        // Assert
        assertEquals(1, result.getIdPerfilNutricional());
        assertEquals(65.0f, result.getPeso());
        assertEquals(1.70f, result.getAltura());
        verify(perfilNutricionalRepository, times(1)).save(perfilToCreate);
    }

    @Test
    void updatePerfilNutricional_existingId_shouldUpdateAndReturnPerfilNutricional() {
        // Arrange
        int perfilId = 1;
        PerfilNutricional existingPerfil = new PerfilNutricional();
        existingPerfil.setIdPerfilNutricional(perfilId);
        existingPerfil.setPeso(70.0f);
        PerfilNutricional updatedPerfilDetails = new PerfilNutricional();
        updatedPerfilDetails.setPeso(72.0f);

        when(perfilNutricionalRepository.findById(perfilId)).thenReturn(Optional.of(existingPerfil));
        when(perfilNutricionalRepository.save(any(PerfilNutricional.class))).thenReturn(existingPerfil);

        // Act
        PerfilNutricional result = perfilNutricionalController.updatePerfilNutricional(perfilId, updatedPerfilDetails);

        // Assert
        assertEquals(72.0f, result.getPeso());
        verify(perfilNutricionalRepository, times(1)).findById(perfilId);
        verify(perfilNutricionalRepository, times(1)).save(any(PerfilNutricional.class));
    }

    @Test
    void updatePerfilNutricional_nonExistingId_shouldReturnNull() {
        // Arrange
        int perfilId = 1;
        PerfilNutricional updatedPerfilDetails = new PerfilNutricional();
        updatedPerfilDetails.setPeso(72.0f);

        when(perfilNutricionalRepository.findById(perfilId)).thenReturn(Optional.empty());

        // Act
        PerfilNutricional result = perfilNutricionalController.updatePerfilNutricional(perfilId, updatedPerfilDetails);

        // Assert
        assertNull(result);
        verify(perfilNutricionalRepository, times(1)).findById(perfilId);
        verify(perfilNutricionalRepository, never()).save(any(PerfilNutricional.class));
    }

    @Test
    void deletePerfilNutricional_existingId_shouldDeletePerfilNutricional() {
        // Arrange
        int perfilId = 1;
        doNothing().when(perfilNutricionalRepository).deleteById(perfilId);

        // Act
        perfilNutricionalController.deletePerfilNutricional(perfilId);

        // Assert
        verify(perfilNutricionalRepository, times(1)).deleteById(perfilId);
    }
}