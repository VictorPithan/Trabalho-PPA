package sqlite.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.entidade.Curso;
import persistence.ICursoRepository;

public class CursoRepository implements ICursoRepository {
  private String dbname = "mochinho.db";

  @Override
  public void save(Curso curso) {
    try {
      Connection con = ConexaoSqlite.getInstance(dbname);
      String sql = "INSERT INTO curso (codigo, nome, ementa, cargaHoraria, vagas, inscritos, dataInicio, idadeMinima) VALUES (?,?,?,?,?,?,?,?);";
      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setInt(1, curso.getCodigo());
      stmt.setString(2, curso.getNome());
      stmt.setString(3, curso.getEmenta());
      stmt.setInt(4, curso.getCargaHoraria());
      stmt.setInt(5, curso.getVagas());
      stmt.setInt(6, curso.getInscritos());
      stmt.setString(7, curso.getDataInicio());
      stmt.setInt(8, curso.getIdadeMinima());

      stmt.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Curso> findByCodigo(Integer codigoCurso) {
    try {
      Connection con = ConexaoSqlite.getInstance(dbname);

      String sql = "SELECT * FROM curso WHERE codigo = ?";

      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setInt(1, codigoCurso);

      ResultSet result = stmt.executeQuery();

      Curso curso = null;

      if (result.next()) {
        curso = new Curso();
        curso.setCodigo(result.getInt("codigo"));
        curso.setNome(result.getString("nome"));
        curso.setEmenta(result.getString("ementa"));
        curso.setCargaHoraria(result.getInt("cargaHoraria"));
        curso.setVagas(result.getInt("vagas"));
        curso.setInscritos(result.getInt("inscritos"));
        curso.setDataInicio(result.getString("dataInicio"));
        curso.setIdadeMinima(result.getInt("idadeMinima"));
      }
      result.close();
      return Optional.of(curso);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.empty();

  }

  // @Override
  // public void update(Curso curso) {
  // cursos.stream().filter(a -> a.equals(curso)).findFirst().ifPresent(c -> {
  // cursos.remove(c);
  // cursos.add(curso);
  // });
  // }

}
