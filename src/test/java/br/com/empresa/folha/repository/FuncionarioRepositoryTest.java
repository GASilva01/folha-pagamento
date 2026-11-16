package br.com.empresa.folha.repository;
import br.com.empresa.folha.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FuncionarioRepositoryTest {
    @Test void armazenaBuscaListaRemoveEVerificaCpf() {
        FuncionarioRepository r = new FuncionarioRepository(); Funcionario f = new FuncionarioPJ(1,"A","52998224725",10,2);
        r.salvar(f); assertTrue(r.existeCodigo(1)); assertTrue(r.existeCpf("529.982.247-25"));
        assertEquals(f, r.buscarPorCodigo(1).orElseThrow()); assertEquals(1, r.listarTodos().size());
        assertTrue(r.remover(1)); assertFalse(r.remover(1)); assertTrue(r.listarTodos().isEmpty());
    }
}
