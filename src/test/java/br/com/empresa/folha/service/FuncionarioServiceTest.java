package br.com.empresa.folha.service;
import br.com.empresa.folha.exception.*;
import br.com.empresa.folha.model.*;
import br.com.empresa.folha.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {
    private FuncionarioService novo() { return new FuncionarioService(new FuncionarioRepository()); }
    @Test void cadastraBuscaSobrecarregadaListaRelatorioETotaliza() {
        FuncionarioService s = novo(); Funcionario f = new FuncionarioCLT(1,"Ana Silva","52998224725",1000,200);
        s.cadastrarFuncionario(f); s.cadastrarFuncionario(new FuncionarioPJ(2,"Bia","11144477735",500,2));
        assertEquals(f, s.buscarFuncionario(1)); assertEquals(f, s.buscarFuncionario("529.982.247-25"));
        assertEquals(1, s.buscarFuncionario("Ana", false).size()); assertEquals(2200, s.calcularTotalFolha());
        assertEquals(2, s.listarFuncionarios().size()); assertTrue(s.gerarRelatorio().contains("TOTAL BRUTO: R$ 2200,00") || s.gerarRelatorio().contains("TOTAL BRUTO: R$ 2200.00"));
    }
    @Test void rejeitaDuplicidadeEFuncionarioAusente() {
        FuncionarioService s = novo(); s.cadastrarFuncionario(new FuncionarioPJ(1,"A","52998224725",1,1));
        assertThrows(FuncionarioJaCadastradoException.class, () -> s.cadastrarFuncionario(new FuncionarioPJ(1,"B","11144477735",1,1)));
        assertThrows(FuncionarioJaCadastradoException.class, () -> s.cadastrarFuncionario(new FuncionarioPJ(2,"C","52998224725",1,1)));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> s.buscarFuncionario(99));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> s.buscarFuncionario("12345678909"));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> s.removerFuncionario(99));
        s.removerFuncionario(1); assertTrue(s.listarFuncionarios().isEmpty());
    }
    @Test void calculaPolimorficamenteCategoriasDiferentes() {
        Funcionario[] fs = {new FuncionarioCLT(1,"A","52998224725",100,10), new FuncionarioPJ(2,"B","11144477735",20,3), new FuncionarioComissionado(3,"C","12345678909",100,.1,200)};
        assertArrayEquals(new double[]{110,60,120}, java.util.Arrays.stream(fs).mapToDouble(Funcionario::calcularRemuneracao).toArray());
    }
}
