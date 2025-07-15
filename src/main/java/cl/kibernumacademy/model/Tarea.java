package cl.kibernumacademy.model;

public class Tarea {
    private  int id;
    private String titulo;
    private String descripcion;
    private Estado estado;

    private static int nextId;

    public enum Estado {
        PENDIENTE, COMPLETADA
    }

    public Tarea(String titulo, String descripcion) {
        this.id = this.nextId++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = Estado.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void completar() {
        this.estado = Estado.COMPLETADA;
    }

}
