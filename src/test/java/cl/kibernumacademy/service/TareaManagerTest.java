package cl.kibernumacademy.service;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import cl.kibernumacademy.model.Tarea;

public class TareaManagerTest {
    private TareaManager manager;

    @BeforeClass
    public void setup(){
        manager = new TareaManager();
    }

    @AfterClass
    public void resetear(){
        manager = null;
    }
    
    @BeforeMethod
    public void limpiarTareas() {
        manager.getTareas().clear();
    }

    @Test
    @Parameters({"titulo","descripcion"})
    public void testAgregarTarea(String titulo, String descripcion){
        //Tarea.Estado.COMPLETADA
        Tarea tarea = new Tarea(titulo, descripcion);
        System.out.println("El titulo es :"+titulo+", la descripcion es :"+descripcion);
        manager.agregarTarea(tarea);
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(manager.getTareas().contains(tarea), "La tarea debe estar en la lista");
        sa.assertAll(); 
    }

    @Test
    @Parameters({"titulo", "descripcion"})
    public void testCambiarEstadoTarea(String titulo, String descripcion){
        Tarea tarea = new Tarea(titulo, descripcion);
        manager.agregarTarea(tarea);
        boolean completada = manager.completarTarea(tarea);
        SoftAssert sa = new SoftAssert();
        assertTrue(completada, "La tarea debe estar completada");
        assertEquals(tarea.getEstado(), Tarea.Estado.COMPLETADA);
        sa.assertAll(); 
    }

    @Test
    public void testCompletarTareaInexistente(){
        Tarea tarea = new Tarea("Prueba de tarea", "descripcion de prueba");
        boolean completada = manager.completarTarea(tarea);
        assertFalse(completada, "no debe completar una tarea inexistente");
    }

    @Test
    public void testFiltrarPorEstado() {

        Tarea tarea1 = new Tarea("titulo tarea 1","descripcion tarea 1");
        Tarea tarea2 = new Tarea("titulo tarea 2","descripcion tarea 2");
        manager.agregarTarea(tarea1);
        manager.agregarTarea(tarea2);
        
        manager.completarTarea(tarea2);
        List<Tarea> completadas = manager.filtrarTareaPorEstado(Tarea.Estado.COMPLETADA);
        List<Tarea> pendientes = manager.filtrarTareaPorEstado(Tarea.Estado.PENDIENTE);


        SoftAssert sa = new SoftAssert(); 
        sa.assertTrue(completadas.contains(tarea2)); 
        sa.assertTrue(pendientes.contains(tarea1));
        sa.assertFalse(pendientes.contains(tarea2));
        sa.assertAll(); 
    }
    
    
}
