package modelo.entidade;

public class Matricula {

  private Integer numero;
  private final String cpf;
  private final Integer codigoCurso;

  public Matricula(Integer numero, String cpf, Integer codigoCurso) {
    this.cpf = cpf;
    this.codigoCurso = codigoCurso;
  }

  public Integer getCodigoCurso() {
    return codigoCurso;
  }

  public String getCpf() {
    return cpf;
  }


  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }
  
}
