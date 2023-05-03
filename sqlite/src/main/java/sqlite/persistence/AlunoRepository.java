package sqlite.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.entidade.Aluno;
import persistence.IAlunoRepository;

public class AlunoRepository implements IAlunoRepository {

  List<Aluno> alunos = new ArrayList<Aluno>();

  @Override
  public void save(Aluno aluno) {
    alunos.add(aluno);
  }

  @Override
  public Optional<Aluno> findByCpf(String cpf) {
    return alunos
      .stream()
      .filter(a -> a.getCpf().equals(cpf))
      .findAny();
  }
}
