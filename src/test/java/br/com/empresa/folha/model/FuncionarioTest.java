package br.com.empresa.folha.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FuncionarioTest {
    @Test void codigoEIdentidadeSaoConsistentesEntreSubclasses() {
        Funcionario a = new FuncionarioCLT(7,"Ana","52998224725",100,0);
        Funcionario b = new FuncionarioPJ(7,"Bia","11144477735",10,1);
        assertEquals(a, b); assertEquals(a.hashCode(), b.hashCode()); assertEquals(7, a.getCodigo());
    }
    @Test void rejeitaCodigoObrigatorioInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioPJ(0,"Bia","11144477735",10,1));
    }
}
