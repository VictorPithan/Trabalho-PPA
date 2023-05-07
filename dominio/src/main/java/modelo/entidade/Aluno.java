package modelo.entidade;

import java.time.LocalDate;

public class Aluno {

  private Integer id;
  private String cpf;
  private String nome;
  private String email;
  private LocalDate dataNascimento;

  public Aluno() {}

  public Aluno(Integer id, String cpf, String nome, String email) {
    this.id = id;
    this.cpf = cpf;
    this.nome = nome;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Aluno other = (Aluno) obj;
    if (cpf == null) {
      if (other.cpf != null)
        return false;
    } else if (!cpf.equals(other.cpf))
      return false;
    return true;
  }
  @Override
  public String toString() {
    return "Aluno [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + "]";
  }
}