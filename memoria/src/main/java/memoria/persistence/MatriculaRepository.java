package memoria.persistence;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.Matricula;
import persistence.IMatriculaRepository;

public class MatriculaRepository implements IMatriculaRepository {

  List<Matricula> matriculas = new ArrayList<Matricula>();

  @Override
  public void save(Matricula matricula) {
    matriculas.add(matricula);
  }
  
}
