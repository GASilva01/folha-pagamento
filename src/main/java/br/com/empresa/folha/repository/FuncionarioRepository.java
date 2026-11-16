package br.com.empresa.folha.repository;

import br.com.empresa.folha.model.Funcionario;
import java.util.*;

public class FuncionarioRepository {
    private final Map<Integer, Funcionario> funcionarios = new LinkedHashMap<>();
    public void salvar(Funcionario funcionario) { funcionarios.put(funcionario.getIdentificador(), funcionario); }
    public Optional<Funcionario> buscarPorCodigo(int codigo) { return Optional.ofNullable(funcionarios.get(codigo)); }
    public List<Funcionario> listarTodos() { return List.copyOf(funcionarios.values()); }
    public boolean remover(int codigo) { return funcionarios.remove(codigo) != null; }
    public boolean existeCodigo(int codigo) { return funcionarios.containsKey(codigo); }
    public boolean existeCpf(String cpf) {
        return funcionarios.values().stream().anyMatch(f -> f.getCpf().equals(cpf.replaceAll("\\D", "")));
    }
}
