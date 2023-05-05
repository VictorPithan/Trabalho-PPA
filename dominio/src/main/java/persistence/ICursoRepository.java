package persistence;

import java.util.Optional;

import modelo.entidade.Curso;

public interface ICursoRepository {

  Optional<Curso> findByCodigo(Integer codigoCurso);

  // void update(Curso curso);

  void save(Curso curso);

}
