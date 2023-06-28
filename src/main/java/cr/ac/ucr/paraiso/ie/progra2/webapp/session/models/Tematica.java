package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Tematica {

    private int tematicaID;
    private String nombreTematica;

    public Tematica() {
    }

    public Tematica(int tematicaID, String nombreTematica) {
        this.tematicaID = tematicaID;
        this.nombreTematica = nombreTematica;
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
}
