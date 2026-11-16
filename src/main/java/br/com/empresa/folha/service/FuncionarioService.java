package br.com.empresa.folha.service;

import br.com.empresa.folha.exception.FuncionarioJaCadastradoException;
import br.com.empresa.folha.exception.FuncionarioNaoEncontradoException;
import br.com.empresa.folha.model.Funcionario;
import br.com.empresa.folha.repository.FuncionarioRepository;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioRepository repository;
    public FuncionarioService(FuncionarioRepository repository) {
        if (repository == null) throw new IllegalArgumentException("O repositório é obrigatório.");
        this.repository = repository;
    }
    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) throw new IllegalArgumentException("O funcionário é obrigatório.");
        if (repository.existeCodigo(funcionario.getIdentificador()) || repository.existeCpf(funcionario.getCpf()))
            throw new FuncionarioJaCadastradoException("Já existe funcionário com esse código ou CPF.");
        repository.salvar(funcionario);
    }
    public List<Funcionario> listarFuncionarios() { return repository.listarTodos(); }
    public Funcionario buscarFuncionario(int codigo) {
        return repository.buscarPorCodigo(codigo).orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado: " + codigo));
    }
    public Funcionario buscarFuncionario(String cpf) {
        if (cpf == null) throw new IllegalArgumentException("CPF obrigatório.");
        String normalizado = cpf.replaceAll("\\D", "");
        return repository.listarTodos().stream().filter(f -> f.getCpf().equals(normalizado)).findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado para o CPF informado."));
    }
    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório.");
        String consulta = nome.trim();
        return repository.listarTodos().stream()
                .filter(f -> buscaExata ? f.getNome().equalsIgnoreCase(consulta) : f.getNome().toLowerCase().contains(consulta.toLowerCase()))
                .toList();
    }
    public void removerFuncionario(int codigo) {
        if (!repository.remover(codigo)) throw new FuncionarioNaoEncontradoException("Funcionário não encontrado: " + codigo);
    }
    public double calcularTotalFolha() { return repository.listarTodos().stream().mapToDouble(Funcionario::calcularRemuneracao).sum(); }
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder("RELATÓRIO DA FOLHA\n");
        for (Funcionario f : repository.listarTodos())
            sb.append(f).append(String.format(" | Bruto: R$ %.2f | Líquido: R$ %.2f%n", f.calcularRemuneracao(), f.calcularRemuneracaoLiquida()));
        sb.append(String.format("TOTAL BRUTO: R$ %.2f", calcularTotalFolha()));
        return sb.toString();
    }
}
