package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Profesor {
    private String nombre;
    private String apellidos;
    private String carnet;
    private String curso;

    public Profesor() {
    }

    public Profesor(String nombre, String apellidos, String carnet, String curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.carnet = carnet;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getCurso() {
        return curso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
