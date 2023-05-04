package modelo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import modelo.entidade.Aluno;
import modelo.entidade.Boleto;
import modelo.entidade.Curso;
import modelo.entidade.Matricula;
import persistence.IAlunoRepository;
import persistence.IBoletoRepository;
import persistence.ICursoRepository;
import persistence.IMatriculaRepository;
import persistence.IParametroRepository;


public class MatriculaService { // Use Case (Caso de Uso)

  // dependências (depende da persistência)
  private final IAlunoRepository alunoRepository;
  private final ICursoRepository cursoRepository;
  private final IParametroRepository paramRepository;
  private final IBoletoRepository boletoRepository;
  private final IMatriculaRepository matriculaRepository;
  
  

  public MatriculaService(IAlunoRepository alunoRepository, ICursoRepository cursoRepository,
      IParametroRepository paramRepository, IBoletoRepository boletoRepository,
      IMatriculaRepository matriculaRepository) {
    this.alunoRepository = alunoRepository;
    this.cursoRepository = cursoRepository;
    this.paramRepository = paramRepository;
    this.boletoRepository = boletoRepository;
    this.matriculaRepository = matriculaRepository;
  }



  // Transaction Script (Roteiro da Transação)
  // método com toda a lógica
     //  Output              // Input
  public Matricula matricular(String cpf, Integer codigoCurso) {
    
    Aluno aluno = alunoRepository.findByCpf(cpf)
      .orElseThrow(() -> new ServiceException("Aluno " + cpf + " não existe"));

    Curso curso = cursoRepository.findByCodigo(codigoCurso)
      .orElseThrow(() -> new ServiceException("Curso " + codigoCurso + " não existe"));

    if (curso.getInscritos() >= curso.getVagas()) {
      throw new ServiceException("Curso com vagas esgotadas");
    }

    //Integer dias = paramRepository.findParam("DIAS_ATRASO_MATRICULA")
    //  .map(p -> Integer.parseInt(p))
    //  .orElse(3);
    
    //if (LocalDate.now().isAfter(curso.getDataInicio().plusDays(dias))) {
     // throw new ServiceException("Data inscrição passou mais de " + dias + " dias");
    //}

    List<Boleto> boletos = boletoRepository.findBoletosByCpf(cpf);

    Optional<Boleto> boletoVencido = boletos.stream()
      .filter(b -> ! b.isPago()) // boletos em aberto
      .filter(b -> b.getVencimento().isBefore(LocalDate.now())) // vencidos
      .findAny();

    if (boletoVencido.isPresent()) {
      throw new ServiceException("Aluno tem boletos em aberto, ex: " + boletoVencido.get().getCodigo());
    }

//    int idade = Period.between(aluno.getDataNascimento(), LocalDate.now()).getYears();

//    if (curso.getIdadeMinima() != null && idade < curso.getIdadeMinima()) {
//      throw new ServiceException("Aluno não cumpre idade mínima exigida de " + curso.getIdadeMinima());
//    }

    // início transação
    curso.setInscritos(curso.getInscritos() + 1);

    cursoRepository.update(curso);

    Matricula matricula = new Matricula(cpf, codigoCurso);

    matriculaRepository.save(matricula);
    // fim transação

    return matricula;
  }
}


class CursoController { // /curso/matricular


  void matricular() {

  }
}