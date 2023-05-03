package persistence;

import java.sql.SQLException;
import java.util.Optional;

import modelo.entidade.Aluno;

public interface IAlunoRepository {

  Optional<Aluno> findByCpf(String cpf);

  void save(Aluno aluno);
  
}
