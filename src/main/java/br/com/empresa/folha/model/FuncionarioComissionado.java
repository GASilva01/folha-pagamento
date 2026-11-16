package br.com.empresa.folha.model;

public class FuncionarioComissionado extends Funcionario implements Tributavel {
    private double percentualComissao;
    private double vendas;
    private static final double ALIQUOTA = 0.11;
    public FuncionarioComissionado(int codigo, String nome, String cpf, double salarioBase, double percentualComissao, double vendas) {
        super(codigo, nome, cpf, salarioBase); setPercentualComissao(percentualComissao); setVendas(vendas);
    }
    public double getPercentualComissao() { return percentualComissao; }
    public void setPercentualComissao(double percentual) { validarPercentual(percentual); this.percentualComissao = percentual; }
    public double getVendas() { return vendas; }
    public void setVendas(double vendas) { validarValor(vendas, "Vendas"); this.vendas = vendas; }
    @Override public double calcularRemuneracao() { return getSalarioBase() + vendas * percentualComissao; }
    @Override public double calcularTributos() { return calcularRemuneracao() * ALIQUOTA; }
    @Override public double calcularRemuneracaoLiquida() { return calcularRemuneracao() - calcularTributos(); }
    @Override public String toString() { return super.toString() + String.format(" | Base: R$ %.2f | Vendas: R$ %.2f | Comissão: %.1f%%", getSalarioBase(), vendas, percentualComissao * 100); }
}
