package sqlite.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import modelo.entidade.Aluno;
import modelo.entidade.Boleto;
import persistence.IBoletoRepository;

public class BoletoRepository implements IBoletoRepository {


  @Override
  public void save(Boleto boleto) {
    try {
      Connection con = ConexaoSqlite.getInstance();
      String sql = "INSERT INTO boleto (codigo, valor, vencimento, pago, alunoId) VALUES (?,?,?,?,?);";
      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setInt(1, boleto.getCodigo());
      stmt.setBigDecimal(2, boleto.getValor());
      stmt.setString(3, boleto.getVencimento().toString());
      stmt.setBoolean(4, boleto.getPago());
      stmt.setInt(5, boleto.getAlunoContratante().getId());

      stmt.execute();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  

  @Override
  public List<Boleto> findBoletosByCpf(String cpf) {
    List<Boleto> listaBoletos = new ArrayList<>();
    try {
      Connection con = ConexaoSqlite.getInstance();

      AlunoRepository alunoRepository = new AlunoRepository();
      Integer alunoId = alunoRepository.findByCpf(cpf).get().getId();


      String sql = "SELECT * FROM boleto WHERE codigo = ?";

      PreparedStatement stmt = con.prepareStatement(sql);

      stmt.setInt(1, alunoId);

      ResultSet result = stmt.executeQuery();

      Boleto boleto;
      if (result.next()) {
        boleto = new Boleto();
        boleto.setCodigo(result.getInt("codigo"));
        boleto.setValor(result.getBigDecimal("valor"));
        boleto.setVencimento(LocalDate.parse(result.getString("vencimento")));
        boleto.setPago(result.getBoolean("pago"));

//        Integer newAlunoId = result.getInt("alunoId");
        Aluno newAluno = alunoRepository.findByCpf(cpf).get();
        boleto.setAlunoContratante(newAluno);
        listaBoletos.add(boleto);
      }
      result.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return listaBoletos;
  }

}
