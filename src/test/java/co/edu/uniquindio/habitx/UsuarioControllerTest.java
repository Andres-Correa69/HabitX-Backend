package co.edu.uniquindio.habitx;

import co.edu.uniquindio.habitx.controller.UsuarioController;
import co.edu.uniquindio.habitx.model.Usuario;
import co.edu.uniquindio.habitx.repositories.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    public UsuarioControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsuarios_shouldReturnListOfUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1);
        usuario1.setNombre("Andres");
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2);
        usuario2.setNombre("Maria");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> result = usuarioController.getAllUsuarios();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Andres", result.get(0).getNombre());
        assertEquals("Maria", result.get(1).getNombre());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void getUsuarioById_existingId_shouldReturnUsuario() {
        // Arrange
        int usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        usuario.setNombre("Andres");

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        // Act
        Usuario result = usuarioController.getUsuarioById(usuarioId);

        // Assert
        assertEquals("Andres", result.getNombre());
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }

    @Test
    void getUsuarioById_nonExistingId_shouldReturnNull() {
        // Arrange
        int usuarioId = 1;
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        // Act
        Usuario result = usuarioController.getUsuarioById(usuarioId);

        // Assert
        assertEquals(null, result);
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }

    @Test
    void createUsuario_shouldSaveAndReturnUsuario() {
        // Arrange
        Usuario usuarioToCreate = new Usuario();
        usuarioToCreate.setNombre("Carlos");
        Usuario savedUsuario = new Usuario();
        savedUsuario.setIdUsuario(3);
        savedUsuario.setNombre("Carlos");

        when(usuarioRepository.save(usuarioToCreate)).thenReturn(savedUsuario);

        // Act
        Usuario result = usuarioController.createUsuario(usuarioToCreate);

        // Assert
        assertEquals(3, result.getIdUsuario());
        assertEquals("Carlos", result.getNombre());
        verify(usuarioRepository, times(1)).save(usuarioToCreate);
    }

    @Test
    void updateUsuario_existingId_shouldUpdateAndReturnUsuario() {
        // Arrange
        int usuarioId = 1;
        Usuario existingUsuario = new Usuario();
        existingUsuario.setIdUsuario(usuarioId);
        existingUsuario.setNombre("Andres");
        Usuario updatedUsuarioDetails = new Usuario();
        updatedUsuarioDetails.setNombre("Andres Felipe");

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(existingUsuario);

        // Act
        Usuario result = usuarioController.updateUsuario(usuarioId, updatedUsuarioDetails);

        // Assert
        assertEquals("Andres Felipe", result.getNombre());
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void updateUsuario_nonExistingId_shouldReturnNull() {
        // Arrange
        int usuarioId = 1;
        Usuario updatedUsuarioDetails = new Usuario();
        updatedUsuarioDetails.setNombre("Andres Felipe");

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        // Act
        Usuario result = usuarioController.updateUsuario(usuarioId, updatedUsuarioDetails);

        // Assert
        assertEquals(null, result);
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deleteUsuario_existingId_shouldDeleteUsuario() {
        // Arrange
        int usuarioId = 1;
        doNothing().when(usuarioRepository).deleteById(usuarioId);

        // Act
        usuarioController.deleteUsuario(usuarioId);

        // Assert
        verify(usuarioRepository, times(1)).deleteById(usuarioId);
    }
}
