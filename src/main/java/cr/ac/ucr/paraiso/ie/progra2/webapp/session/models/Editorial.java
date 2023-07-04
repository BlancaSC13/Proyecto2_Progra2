package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Editorial {

    private int editorialID;
    private String nombreEditorial;
    private static int contador = 1;

    public Editorial() {
    }

    public Editorial(String nombreEditorial) {
        this.editorialID = contador;
        this.nombreEditorial = nombreEditorial;
        contador++;
    }

    public int getEditorialID() {
        return editorialID;
    }

    public void setEditorialID(int editorialID) {
        this.editorialID = editorialID;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    @Override
    public String toString() {
        return editorialID + nombreEditorial;
    }
}
