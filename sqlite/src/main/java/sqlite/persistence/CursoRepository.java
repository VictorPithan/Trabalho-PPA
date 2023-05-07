package sqlite.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.entidade.Curso;
import persistence.ICursoRepository;

public class CursoRepository implements ICursoRepository {

  @Override
  public void save(Curso curso) {
    try {
      Connection con = ConexaoSqlite.getInstance();
      String sql = "INSERT INTO curso (codigo, nome, ementa, cargaHoraria, vagas, incritos, dataInicio, idadeMinima) VALUES (?,?,?,?,?,?,?,?);";
      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setInt(1, curso.getCodigo());
      stmt.setString(2, curso.getNome());
      stmt.setString(3, curso.getEmenta());
      stmt.setInt(4, curso.getCargaHoraria());
      stmt.setInt(5, curso.getVagas());
      stmt.setInt(6, curso.getInscritos());
      stmt.setString(7, curso.getDataInicio().toString());
      stmt.setInt(8, curso.getIdadeMinima());

      stmt.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Curso> findByCodigo(Integer codigoCurso) {
    try {
      Connection con = ConexaoSqlite.getInstance();

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
        curso.setInscritos(result.getInt("incritos"));
        curso.setDataInicio(LocalDate.parse(result.getString("dataInicio")));
        curso.setIdadeMinima(result.getInt("idadeMinima"));
      }
      result.close();
      return Optional.of(curso);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.empty();

  }

   @Override
   public void update(Curso curso) {
    try {
      Connection con = ConexaoSqlite.getInstance();
      String sql = "UPDATE curso SET codigo = ?, nome = ?, ementa = ?, cargaHoraria = ?, vagas = ?, incritos = ?, dataInicio = ?, idadeMinima = ? WHERE codigo = ?";
//      String sql = "INSERT INTO curso (codigo, nome, ementa, cargaHoraria, vagas, incritos, dataInicio, idadeMinima) VALUES (?,?,?,?,?,?,?,?);";
      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setInt(1, curso.getCodigo());
      stmt.setString(2, curso.getNome());
      stmt.setString(3, curso.getEmenta());
      stmt.setInt(4, curso.getCargaHoraria());
      stmt.setInt(5, curso.getVagas());
      stmt.setInt(6, curso.getInscritos());
      stmt.setString(7, curso.getDataInicio().toString());
      stmt.setInt(8, curso.getIdadeMinima());
      stmt.setInt(9, curso.getCodigo());

      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
