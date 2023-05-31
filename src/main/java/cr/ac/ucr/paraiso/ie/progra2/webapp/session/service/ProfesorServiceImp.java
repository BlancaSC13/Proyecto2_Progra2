package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Profesor;

import java.util.Arrays;
import java.util.List;

public class ProfesorServiceImp implements ProfesorService{

    @Override
    public List<Profesor> listar() {
        return Arrays.asList(new Profesor("Carlos","Sanabria","P2344","Cálculo"),
                new Profesor("Mariana","López","P1234","Ética"),
                new Profesor("Rita","Cantillo","P4567","Programación II"),
                new Profesor("Rodrigo","Cordero","P9876","Estadística"));
    }
}
