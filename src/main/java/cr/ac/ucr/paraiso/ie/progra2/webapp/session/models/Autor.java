package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Autor {

    private int autorID;
    private String nombreAutor;
    private String apellidoAutor;

    public Autor() {
    }

    public Autor(int autorID, String nombreAutor, String apellidoAutor) {
        this.autorID = autorID;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
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
}
