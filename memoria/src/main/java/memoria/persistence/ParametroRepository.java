package memoria.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import persistence.IParametroRepository;

public class ParametroRepository implements IParametroRepository {
  HashMap<String, String> mapParams = criarHashMap();

  @Override
  public Optional<String> findParam(String nome) {
    String diasAtraso = mapParams.get("DIAS_ATRASO_MATRICULA");
    return Optional.ofNullable(diasAtraso);
  }

  public static HashMap<String, String> criarHashMap() {
    // Criando o HashMap e adicionando valores
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("DIAS_ATRASO_MATRICULA", "7");

    // Retornando o HashMap criado
    return hashMap;
  }
  
}
