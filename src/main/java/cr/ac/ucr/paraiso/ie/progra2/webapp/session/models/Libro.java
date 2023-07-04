package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

import java.util.ArrayList;
import java.util.List;

public class Libro {

    private int libroID;
    private int ISBN;
    private String titulo;
    private List<Autor> autores;
    private Editorial editorial;
    private Tematica tematica;
    private static int contador = 1;

    public Libro() {
        autores = new ArrayList<>();
        editorial = new Editorial();
        tematica = new Tematica();
    }

    public Libro(int ISBN, String titulo, List<Autor> autores, Editorial editorial, Tematica tematica) {
        this.libroID = contador;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.tematica = tematica;
        contador++;
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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "libroID=" + libroID +
                ", ISBN=" + ISBN +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", editorial=" + editorial +
                ", tematica=" + tematica +
                '}';
    }
}
