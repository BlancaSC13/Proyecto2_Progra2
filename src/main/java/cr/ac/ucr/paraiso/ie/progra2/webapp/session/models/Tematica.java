package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Tematica {

    private int tematicaID;
    private String nombreTematica;
    private static int contador=1;

    public Tematica() {
    }

    public Tematica(String nombreTematica) {
        this.tematicaID = contador;
        this.nombreTematica = nombreTematica;
        contador++;
    }

    public int getTematicaID() {
        return tematicaID;
    }

    public void setTematicaID(int tematicaID) {
        this.tematicaID = tematicaID;
    }

    public String getNombreTematica() {
        return nombreTematica;
    }

    public void setNombreTematica(String nombreTematica) {
        this.nombreTematica = nombreTematica;
    }

    @Override
    public String toString() {
        return nombreTematica;
    }
}
