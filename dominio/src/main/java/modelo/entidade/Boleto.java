package modelo.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Boleto {

  private Integer codigo;
  private BigDecimal valor; // monetário/dinheiro (decimal, Currency, Money)
  private LocalDate vencimento;
  private Boolean pago;
  private Aluno alunoContratante;

  public Integer getCodigo() {
    return codigo;
  }
  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }
  public BigDecimal getValor() {
    return valor;
  }
  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }
  public LocalDate getVencimento() {
    return vencimento;
  }
  public void setVencimento(LocalDate vencimento) {
    this.vencimento = vencimento;
  }
  public Boolean isPago() {
    return pago;
  }
  public void setPago(Boolean pago) {
    this.pago = pago;
  }
  public Boolean getPago() {
    return pago;
  }
  public Aluno getAlunoContratante() {
    return alunoContratante;
  }
  public void setAlunoContratante(Aluno alunoContratante) {
    this.alunoContratante = alunoContratante;
  }

  

}
