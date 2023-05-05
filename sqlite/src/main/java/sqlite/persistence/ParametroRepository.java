package sqlite.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import modelo.entidade.Aluno;
import persistence.IParametroRepository;

public class ParametroRepository implements IParametroRepository {
  private String dbname = "mochinho.db";
  @Override
  public Optional<String> findParam(String key) {
    try {
      Connection con = ConexaoSqlite.getInstance();
      String sql = "SELECT value FROM params WHERE key = ?";

      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setString(1, key);

      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        return Optional.ofNullable(result.getString("value"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }
  
}
