package br.com.empresa.folha.view;

import br.com.empresa.folha.exception.FuncionarioJaCadastradoException;
import br.com.empresa.folha.exception.FuncionarioNaoEncontradoException;
import br.com.empresa.folha.model.*;
import br.com.empresa.folha.service.FuncionarioService;
import java.util.Scanner;

public class FuncionarioView {
    private final FuncionarioService service;
    private final Scanner scanner;
    public FuncionarioView(FuncionarioService service, Scanner scanner) {
        if (service == null || scanner == null) throw new IllegalArgumentException("Serviço e entrada são obrigatórios.");
        this.service = service; this.scanner = scanner;
    }
    public void iniciar() {
        boolean ativo = true;
        while (ativo) {
            System.out.println("\n=== Folha de Pagamento ===\n1 Cadastrar CLT\n2 Cadastrar PJ\n3 Cadastrar comissionado\n4 Listar\n5 Buscar por código\n6 Remover\n7 Relatório\n0 Sair");
            try {
                switch (lerInt("Opção: ")) {
                    case 1 -> cadastrarClt(); case 2 -> cadastrarPj(); case 3 -> cadastrarComissionado();
                    case 4 -> service.listarFuncionarios().forEach(System.out::println);
                    case 5 -> System.out.println(detalhar(service.buscarFuncionario(lerInt("Código: "))));
                    case 6 -> { service.removerFuncionario(lerInt("Código: ")); System.out.println("Funcionário removido."); }
                    case 7 -> System.out.println(service.gerarRelatorio());
                    case 0 -> ativo = false; default -> System.out.println("Opção inválida.");
                }
            } catch (FuncionarioNaoEncontradoException | FuncionarioJaCadastradoException | IllegalArgumentException e) {
                System.out.println("Não foi possível concluir: " + e.getMessage());
            }
        }
    }
    private void cadastrarClt() {
        int codigo = lerInt("Código: "); String nome = ler("Nome: "); String cpf = ler("CPF: ");
        service.cadastrarFuncionario(new FuncionarioCLT(codigo, nome, cpf, lerDouble("Salário-base: "), lerDouble("Benefício: ")));
    }
    private void cadastrarPj() {
        int codigo = lerInt("Código: "); String nome = ler("Nome: "); String cpf = ler("CPF: ");
        service.cadastrarFuncionario(new FuncionarioPJ(codigo, nome, cpf, lerDouble("Valor por projeto: "), lerInt("Quantidade de projetos: ")));
    }
    private void cadastrarComissionado() {
        int codigo = lerInt("Código: "); String nome = ler("Nome: "); String cpf = ler("CPF: ");
        service.cadastrarFuncionario(new FuncionarioComissionado(codigo, nome, cpf, lerDouble("Salário-base: "), lerDouble("Comissão (%): ") / 100, lerDouble("Vendas: ")));
    }
    private String ler(String prompt) { System.out.print(prompt); return scanner.nextLine(); }
    private int lerInt(String prompt) { return Integer.parseInt(ler(prompt).trim()); }
    private double lerDouble(String prompt) { return Double.parseDouble(ler(prompt).trim().replace(',', '.')); }
    private String detalhar(Funcionario f) {
        return f + String.format(" | Remuneração bruta: R$ %.2f | Tributos: R$ %.2f | Líquida: R$ %.2f",
                f.calcularRemuneracao(), f instanceof Tributavel t ? t.calcularTributos() : 0, f.calcularRemuneracaoLiquida());
    }
}
