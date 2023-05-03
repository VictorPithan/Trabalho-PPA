package persistence;

import java.util.Optional;

public interface IParametroRepository {
  // programação defensiva (pensar no pior)

  Optional<String> findParam(String nome);
  
}
