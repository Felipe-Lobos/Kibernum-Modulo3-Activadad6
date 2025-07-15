package cl.kibernumacademy.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;


import cl.kibernumacademy.model.Tarea;

public class TareaManager {
    private List<Tarea> tareas = new ArrayList<>();

    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public boolean completarTarea(Tarea tarea){
        if (tareas.contains(tarea)){
            tarea.completar();
            return true;
        }
        return false;
    }

    public List<Tarea> filtrarTareaPorEstado(Tarea.Estado estado){
        return tareas.stream()
                .filter(t -> t.getEstado() == estado)
                .collect(Collectors.toList());
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
