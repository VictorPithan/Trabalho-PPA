package sqlite.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidade.Aluno;
import modelo.entidade.Matricula;
import persistence.IMatriculaRepository;

public class MatriculaRepository implements IMatriculaRepository {

  private String dbname = "mochinho.db";

  @Override
  public void save(Matricula matricula) {
    try {
      Connection con = ConexaoSqlite.getInstance();
      String sql = "INSERT INTO matricula (numero, cpf, codigoCurso) VALUES (?,?,?);";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, 1);
      stmt.setString(2, matricula.getCpf());
      stmt.setInt(3, matricula.getCodigoCurso());

      stmt.execute();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
