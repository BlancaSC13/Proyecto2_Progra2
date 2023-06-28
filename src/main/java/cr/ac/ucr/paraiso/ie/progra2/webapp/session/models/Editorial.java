package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Editorial {

    private int editorialID;
    private String nombreEditorial;

    public Editorial() {
    }

    public Editorial(int editorialID, String nombreEditorial) {
        this.editorialID = editorialID;
        this.nombreEditorial = nombreEditorial;
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
}
