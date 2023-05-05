package sqlite.persistence;

import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import modelo.entidade.Aluno;
import persistence.IAlunoRepository;

public class AlunoRepository implements IAlunoRepository {
  private String dbname = "mochinho.db";
  @Override
  public void save(Aluno aluno) {
    try {
      Connection con = ConexaoSqlite.getInstance();
      String sql = "INSERT INTO aluno (id, cpf, nome, email, dataNascimento) VALUES (?,?,?,?,?);";
      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setInt(1, aluno.getId());
      stmt.setString(2, aluno.getCpf());
      stmt.setString(3, aluno.getNome());
      stmt.setString(4, aluno.getEmail());
      if(Objects.isNull(aluno.getDataNascimento())) {
        stmt.setNull(5, Types.VARCHAR);
      }else {
        stmt.setString(5, aluno.getDataNascimento().toString());
      }


      stmt.execute();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Aluno> findByCpf(String cpf) {
    try {
      Connection con = ConexaoSqlite.getInstance();

      String sql = "SELECT * FROM aluno WHERE cpf = ?";

      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setString(1, cpf);

      ResultSet result = stmt.executeQuery();

      Aluno aluno = null;

      if (result.next()) {
        aluno = new Aluno();
        aluno.setId(result.getInt("id"));
        aluno.setCpf(result.getString("cpf"));
        aluno.setNome(result.getString("nome"));
        aluno.setEmail(result.getString("email"));
        try {
          aluno.setDataNascimento(LocalDate.parse(result.getString("dataNascimento")));
        } catch (DateTimeParseException | NullPointerException e) {
          aluno.setDataNascimento(null);
        }

      }
      result.close();
      return Optional.of(aluno);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }
}
