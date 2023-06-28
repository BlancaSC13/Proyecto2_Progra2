package cr.ac.ucr.paraiso.ie.progra2.webapp.session.models;

public class Libro {

    private int libroID;
    private String titulo;
    private Autor autor;
    private Editorial editorial;
    private Tematica tematica;

    public Libro() {
    }

    public Libro(int libroID, String titulo, Autor autor, Editorial editorial, Tematica tematica) {
        this.libroID = libroID;
        this.titulo = titulo;
        this.autor = autor;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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
