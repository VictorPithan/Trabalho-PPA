package persistence;

import java.util.List;

import modelo.entidade.Boleto;

public interface IBoletoRepository {

  void save(Boleto boleto);

  List<Boleto> findBoletosByCpf(String cpf);
  
}
