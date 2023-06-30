package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

import java.util.ArrayList;
import java.util.List;

public class Libro {

    private int libroID;
    private String titulo;
    private List<Autor> autores;
    private Editorial editorial;
    private Tematica tematica;

    public Libro() {
        autores = new ArrayList<>();
        editorial = new Editorial();
        tematica = new Tematica();
    }

    public Libro(int libroID, String titulo, List<Autor> autores, Editorial editorial, Tematica tematica) {
        this.libroID = libroID;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.tematica = tematica;
    }

    public int getLibroID() {
        return libroID;
    }

    public void setLibroID(int libroID) {
        this.libroID = libroID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }
}
