package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Curso {

    private int id;
    private String nombre;

    private String carrera;

    private String semestre;

    public Curso(int id, String nombre, String carrera, String semestre) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
