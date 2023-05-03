package sqlite.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.entidade.Curso;
import persistence.ICursoRepository;

public class CursoRepository implements ICursoRepository {

  List<Curso> cursos = new ArrayList<>();

  @Override
  public void save(Curso curso) {
    cursos.add(curso);
  }


  @Override
  public Optional<Curso> findByCodigo(Integer codigoCurso) {
    return cursos.stream().filter(a -> a.getCodigo().equals(codigoCurso)).findAny();
  }

  @Override
  public void update(Curso curso) {
    cursos.stream().filter(a -> a.equals(curso)).findFirst().ifPresent(c -> {
      cursos.remove(c);
      cursos.add(curso);
    });
  }
  
}
