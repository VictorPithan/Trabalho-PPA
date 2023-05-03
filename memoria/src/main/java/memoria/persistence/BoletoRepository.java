package memoria.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import modelo.entidade.Boleto;
import persistence.IBoletoRepository;

public class BoletoRepository implements IBoletoRepository {

  List<Boleto> listaBoletos = new ArrayList<Boleto>();

  @Override
  public void save(Boleto boleto) {
    listaBoletos.add(boleto);
  }
  

  @Override
  public List<Boleto> findBoletosByCpf(String cpf) {
    return listaBoletos
      .stream()
      .filter(boleto -> boleto.getAlunoContratante().getCpf().equals(cpf))
      .toList();
  }


}
