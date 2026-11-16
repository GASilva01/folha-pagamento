package br.com.empresa.folha.model;

import java.util.Objects;

public abstract class Funcionario implements Identificavel {
    private final int codigo;
    private String nome;
    private String cpf;
    private double salarioBase;

    protected Funcionario(int codigo, String nome, String cpf, double salarioBase) {
        if (codigo <= 0) throw new IllegalArgumentException("O código deve ser positivo.");
        this.codigo = codigo;
        setNome(nome);
        setCpf(cpf);
        setSalarioBase(salarioBase);
    }

    @Override public final int getIdentificador() { return codigo; }
    public final int getCodigo() { return codigo; }
    public final String getNome() { return nome; }
    public final void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome é obrigatório.");
        this.nome = nome.trim();
    }
    public final String getCpf() { return cpf; }
    public final void setCpf(String cpf) {
        if (!cpfValido(cpf)) throw new IllegalArgumentException("CPF inválido.");
        this.cpf = cpf.replaceAll("\\D", "");
    }
    public final double getSalarioBase() { return salarioBase; }
    public final void setSalarioBase(double salarioBase) {
        validarValor(salarioBase, "Salário-base");
        this.salarioBase = salarioBase;
    }
    public abstract double calcularRemuneracao();
    public abstract double calcularRemuneracaoLiquida();

    protected static void validarValor(double valor, String campo) {
        if (!Double.isFinite(valor) || valor < 0) throw new IllegalArgumentException(campo + " não pode ser negativo ou inválido.");
    }
    protected static void validarPercentual(double percentual) {
        if (!Double.isFinite(percentual) || percentual < 0 || percentual > 1)
            throw new IllegalArgumentException("O percentual deve estar entre 0 e 1.");
    }
    private static boolean cpfValido(String cpf) {
        if (cpf == null) return false;
        String n = cpf.replaceAll("\\D", "");
        if (!n.matches("\\d{11}") || n.chars().distinct().count() == 1) return false;
        int soma = 0;
        for (int i = 0; i < 9; i++) soma += (n.charAt(i) - '0') * (10 - i);
        int d1 = 11 - soma % 11; if (d1 >= 10) d1 = 0;
        soma = 0;
        for (int i = 0; i < 10; i++) soma += (n.charAt(i) - '0') * (11 - i);
        int d2 = 11 - soma % 11; if (d2 >= 10) d2 = 0;
        return d1 == n.charAt(9) - '0' && d2 == n.charAt(10) - '0';
    }
    @Override public String toString() {
        return "Código: " + codigo + " | Nome: " + nome + " | CPF: " + cpf + " | Tipo: " + getClass().getSimpleName();
    }
    @Override public final boolean equals(Object obj) {
        return this == obj || (obj instanceof Funcionario other && codigo == other.codigo);
    }
    @Override public final int hashCode() { return Objects.hash(codigo); }
}
