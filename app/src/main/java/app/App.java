package app;

import java.time.LocalDate;
import java.util.Date;

import sqlite.persistence.AlunoRepository;
import memoria.persistence.BoletoRepository;
import sqlite.persistence.CursoRepository;
import sqlite.persistence.MatriculaRepository;
import sqlite.persistence.ParametroRepository;
import modelo.entidade.Aluno;
import modelo.entidade.Curso;
import modelo.entidade.Matricula;
import modelo.service.MatriculaService;
import modelo.service.ServiceException;
import persistence.IAlunoRepository;
import persistence.IBoletoRepository;
import persistence.ICursoRepository;
import persistence.IMatriculaRepository;
import persistence.IParametroRepository;

public class App {

    public static void main(String[] args) {

        IAlunoRepository alunoRepository = new AlunoRepository();
        ICursoRepository cursoRepository = new CursoRepository();
        IParametroRepository paramRepository = new ParametroRepository();
        IMatriculaRepository matriculaRepository = new MatriculaRepository();
        IBoletoRepository boletoRepository = new BoletoRepository();

        Aluno marcio = new Aluno();
        marcio.setId(1);
        marcio.setNome("Marcio");
        marcio.setCpf("12345678910");
        marcio.setEmail("marcio@email.com");
        marcio.setDataNascimento(LocalDate.of(1976,10,25));

        alunoRepository.save(marcio);

        System.out.println(alunoRepository.findByCpf("12345678910"));

        Curso tads = new Curso();
        tads.setCodigo(123456);
        tads.setNome("Tec e Anal e Des Sist");
        tads.setEmenta("Um curso muito legal");
        tads.setCargaHoraria(700);
        tads.setDataInicio(LocalDate.now());
        tads.setIdadeMinima(18);
        tads.setInscritos(18);
        tads.setVagas(20);

        cursoRepository.save(tads);
        //
        System.out.println(cursoRepository.findByCodigo(123456));

//        Matricula mat = new Matricula(1, "12345678910", 123456);
//
//        matriculaRepository.save(mat);

         MatriculaService matriculaService = new MatriculaService(
         alunoRepository,
         cursoRepository,
         paramRepository,
         boletoRepository,
         matriculaRepository);

//         Matricula m = matriculaService.matricular();

         try {
         matriculaService.matricular(2,"12345678910", 123456);
         } catch (ServiceException se) {
         System.err.println(se); // CPF não existe
         }

        try {
            matriculaService.matricular(3, "12345678901", 123);
        } catch (ServiceException se) {
            System.err.println(se); // CPF não existe
        }

    }

}
