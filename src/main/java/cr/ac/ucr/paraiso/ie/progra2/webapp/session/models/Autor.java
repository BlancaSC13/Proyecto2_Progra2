package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Autor {

    private int autorID;
    private String nombreAutor;
    private String apellidoAutor;
    private static int contador = 1;

    public Autor() {
    }

    public Autor(String nombreAutor, String apellidoAutor) {
        this.autorID = contador;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        contador++;
    }

    public int getAutorID() {
        return autorID;
    }

    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "autorID=" + autorID +
                ", nombreAutor='" + nombreAutor + '\'' +
                ", apellidoAutor='" + apellidoAutor + '\'' +
                '}';
    }
}
