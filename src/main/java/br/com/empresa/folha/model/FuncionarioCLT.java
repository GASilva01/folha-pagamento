package br.com.empresa.folha.model;

public class FuncionarioCLT extends Funcionario implements Tributavel {
    private double beneficio;
    private static final double ALIQUOTA = 0.08;
    public FuncionarioCLT(int codigo, String nome, String cpf, double salarioBase, double beneficio) {
        super(codigo, nome, cpf, salarioBase); setBeneficio(beneficio);
    }
    public double getBeneficio() { return beneficio; }
    public void setBeneficio(double beneficio) { validarValor(beneficio, "Benefício"); this.beneficio = beneficio; }
    @Override public double calcularRemuneracao() { return getSalarioBase() + beneficio; }
    @Override public double calcularTributos() { return calcularRemuneracao() * ALIQUOTA; }
    @Override public double calcularRemuneracaoLiquida() { return calcularRemuneracao() - calcularTributos(); }
    @Override public String toString() { return super.toString() + String.format(" | Base: R$ %.2f | Benefício: R$ %.2f", getSalarioBase(), beneficio); }
}
