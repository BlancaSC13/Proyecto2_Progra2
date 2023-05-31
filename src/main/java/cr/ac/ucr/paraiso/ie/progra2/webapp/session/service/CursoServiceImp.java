package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;

import java.util.Arrays;
import java.util.List;

public class CursoServiceImp implements CursoService{
    @Override
    public List<Curso> listar() {
        return Arrays.asList(new Curso(100, "Introducción a la Programación", "Informática","I"),
                new Curso(200, "Algorítmos y Estructuras de datos", "Informática","II"),
                new Curso(300, "Programación 2", "Informática","I"));
    }
}
