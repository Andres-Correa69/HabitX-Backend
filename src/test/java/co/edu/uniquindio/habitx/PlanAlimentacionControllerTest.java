package co.edu.uniquindio.habitx;


import co.edu.uniquindio.habitx.controller.PlanAlimentacionController;
import co.edu.uniquindio.habitx.model.ObjetivoNutricional;
import co.edu.uniquindio.habitx.model.PlanAlimentacion;
import co.edu.uniquindio.habitx.repositories.PlanAlimentacionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PlanAlimentacionControllerTest {

    @InjectMocks
    private PlanAlimentacionController planAlimentacionController;

    @Mock
    private PlanAlimentacionRepository planAlimentacionRepository;

    public PlanAlimentacionControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlanesAlimentacion_shouldReturnListOfPlanes() {
        // Arrange
        PlanAlimentacion plan1 = new PlanAlimentacion();
        plan1.setIdPlanAlimentacion(1);
        plan1.setNombre("Plan Keto");
        PlanAlimentacion plan2 = new PlanAlimentacion();
        plan2.setIdPlanAlimentacion(2);
        plan2.setNombre("Dieta Mediterránea");
        List<PlanAlimentacion> planes = Arrays.asList(plan1, plan2);

        when(planAlimentacionRepository.findAll()).thenReturn(planes);

        // Act
        List<PlanAlimentacion> result = planAlimentacionController.getAllPlanesAlimentacion();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Plan Keto", result.get(0).getNombre());
        assertEquals("Dieta Mediterránea", result.get(1).getNombre());
        verify(planAlimentacionRepository, times(1)).findAll();
    }

    @Test
    void getPlanAlimentacionById_existingId_shouldReturnPlan() {
        // Arrange
        int planId = 1;
        PlanAlimentacion plan = new PlanAlimentacion();
        plan.setIdPlanAlimentacion(planId);
        plan.setNombre("Plan Vegano");

        when(planAlimentacionRepository.findById(planId)).thenReturn(Optional.of(plan));

        // Act
        PlanAlimentacion result = planAlimentacionController.getPlanAlimentacionById(planId);

        // Assert
        assertEquals("Plan Vegano", result.getNombre());
        verify(planAlimentacionRepository, times(1)).findById(planId);
    }

    @Test
    void getPlanAlimentacionById_nonExistingId_shouldReturnNull() {
        // Arrange
        int planId = 1;
        when(planAlimentacionRepository.findById(planId)).thenReturn(Optional.empty());

        // Act
        PlanAlimentacion result = planAlimentacionController.getPlanAlimentacionById(planId);

        // Assert
        assertNull(result);
        verify(planAlimentacionRepository, times(1)).findById(planId);
    }

    @Test
    void getPlanesAlimentacionByObjetivoId_existingObjetivoId_shouldReturnListOfPlanes() {
        // Arrange
        int objetivoId = 1;
        ObjetivoNutricional objetivo = new ObjetivoNutricional();
        objetivo.setIdObjetivoNutricional(objetivoId);
        PlanAlimentacion plan1 = new PlanAlimentacion();
        plan1.setIdPlanAlimentacion(1);
        plan1.setNombre("Plan para bajar de peso");
        plan1.setObjetivoNutricional(objetivo);
        PlanAlimentacion plan2 = new PlanAlimentacion();
        plan2.setIdPlanAlimentacion(2);
        plan2.setNombre("Recetas bajas en calorías");
        plan2.setObjetivoNutricional(objetivo);
        List<PlanAlimentacion> planes = Arrays.asList(plan1, plan2);

        when(planAlimentacionRepository.findByObjetivoNutricionalIdObjetivoNutricional(objetivoId)).thenReturn(planes);

        // Act
        List<PlanAlimentacion> result = planAlimentacionController.getPlanesAlimentacionByObjetivoId(objetivoId);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Plan para bajar de peso", result.get(0).getNombre());
        assertEquals("Recetas bajas en calorías", result.get(1).getNombre());
        assertEquals(objetivoId, result.get(0).getObjetivoNutricional().getIdObjetivoNutricional());
        assertEquals(objetivoId, result.get(1).getObjetivoNutricional().getIdObjetivoNutricional());
        verify(planAlimentacionRepository, times(1)).findByObjetivoNutricionalIdObjetivoNutricional(objetivoId);
    }

    @Test
    void getPlanesAlimentacionByObjetivoId_nonExistingObjetivoId_shouldReturnEmptyList() {
        // Arrange
        int objetivoId = 1;
        when(planAlimentacionRepository.findByObjetivoNutricionalIdObjetivoNutricional(objetivoId)).thenReturn(new ArrayList<>());

        // Act
        List<PlanAlimentacion> result = planAlimentacionController.getPlanesAlimentacionByObjetivoId(objetivoId);

        // Assert
        assertEquals(0, result.size());
        verify(planAlimentacionRepository, times(1)).findByObjetivoNutricionalIdObjetivoNutricional(objetivoId);
    }

    @Test
    void createPlanAlimentacion_shouldSaveAndReturnPlan() {
        // Arrange
        PlanAlimentacion planToCreate = new PlanAlimentacion();
        planToCreate.setNombre("Nuevo Plan");
        planToCreate.setFechaInicio(LocalDateTime.now());
        ObjetivoNutricional objetivo = new ObjetivoNutricional();
        objetivo.setIdObjetivoNutricional(1);
        planToCreate.setObjetivoNutricional(objetivo);
        PlanAlimentacion savedPlan = new PlanAlimentacion();
        savedPlan.setIdPlanAlimentacion(3);
        savedPlan.setNombre("Nuevo Plan");
        savedPlan.setFechaInicio(LocalDateTime.now());
        savedPlan.setObjetivoNutricional(objetivo);

        when(planAlimentacionRepository.save(planToCreate)).thenReturn(savedPlan);

        // Act
        PlanAlimentacion result = planAlimentacionController.createPlanAlimentacion(planToCreate);

        // Assert
        assertEquals(3, result.getIdPlanAlimentacion());
        assertEquals("Nuevo Plan", result.getNombre());
        assertEquals(1, result.getObjetivoNutricional().getIdObjetivoNutricional());
        verify(planAlimentacionRepository, times(1)).save(planToCreate);
    }

    @Test
    void updatePlanAlimentacion_existingId_shouldUpdateAndReturnPlan() {
        // Arrange
        int planId = 1;
        PlanAlimentacion existingPlan = new PlanAlimentacion();
        existingPlan.setIdPlanAlimentacion(planId);
        existingPlan.setNombre("Plan Antiguo");
        LocalDateTime oldDate = LocalDateTime.now().minusDays(7);
        existingPlan.setFechaInicio(oldDate);
        PlanAlimentacion updatedPlanDetails = new PlanAlimentacion();
        updatedPlanDetails.setNombre("Plan Nuevo");
        LocalDateTime newDate = LocalDateTime.now();
        updatedPlanDetails.setFechaInicio(newDate);

        when(planAlimentacionRepository.findById(planId)).thenReturn(Optional.of(existingPlan));
        when(planAlimentacionRepository.save(any(PlanAlimentacion.class))).thenReturn(existingPlan);

        // Act
        PlanAlimentacion result = planAlimentacionController.updatePlanAlimentacion(planId, updatedPlanDetails);

        // Assert
        assertEquals("Plan Nuevo", result.getNombre());
        assertEquals(newDate.getDayOfYear(), result.getFechaInicio().getDayOfYear());
        verify(planAlimentacionRepository, times(1)).findById(planId);
        verify(planAlimentacionRepository, times(1)).save(any(PlanAlimentacion.class));
    }

    @Test
    void updatePlanAlimentacion_nonExistingId_shouldReturnNull() {
        // Arrange
        int planId = 1;
        PlanAlimentacion updatedPlanDetails = new PlanAlimentacion();
        updatedPlanDetails.setNombre("Plan Nuevo");
        updatedPlanDetails.setFechaInicio(LocalDateTime.now());

        when(planAlimentacionRepository.findById(planId)).thenReturn(Optional.empty());

        // Act
        PlanAlimentacion result = planAlimentacionController.updatePlanAlimentacion(planId, updatedPlanDetails);

        // Assert
        assertNull(result);
        verify(planAlimentacionRepository, times(1)).findById(planId);
        verify(planAlimentacionRepository, never()).save(any(PlanAlimentacion.class));
    }

    @Test
    void deletePlanAlimentacion_existingId_shouldDeletePlan() {
        // Arrange
        int planId = 1;
        doNothing().when(planAlimentacionRepository).deleteById(planId);

        // Act
        planAlimentacionController.deletePlanAlimentacion(planId);

        // Assert
        verify(planAlimentacionRepository, times(1)).deleteById(planId);
    }
}