package br.com.empresa.folha.model;

public class FuncionarioPJ extends Funcionario {
    private double valorPorProjeto;
    private int quantidadeProjetos;
    public FuncionarioPJ(int codigo, String nome, String cpf, double valorPorProjeto, int quantidadeProjetos) {
        super(codigo, nome, cpf, 0); setValorPorProjeto(valorPorProjeto); setQuantidadeProjetos(quantidadeProjetos);
    }
    public double getValorPorProjeto() { return valorPorProjeto; }
    public void setValorPorProjeto(double valor) { validarValor(valor, "Valor por projeto"); this.valorPorProjeto = valor; }
    public int getQuantidadeProjetos() { return quantidadeProjetos; }
    public void setQuantidadeProjetos(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("A quantidade de projetos não pode ser negativa.");
        this.quantidadeProjetos = quantidade;
    }
    @Override public double calcularRemuneracao() { return valorPorProjeto * quantidadeProjetos; }
    @Override public double calcularRemuneracaoLiquida() { return calcularRemuneracao(); }
    @Override public String toString() { return super.toString() + String.format(" | Valor/projeto: R$ %.2f | Projetos: %d", valorPorProjeto, quantidadeProjetos); }
}
