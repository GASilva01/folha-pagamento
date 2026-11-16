package br.com.empresa.folha.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioPJTest {
    @Test void calculaPorProjetoEImplementaContratoIdentificavel() {
        FuncionarioPJ f = new FuncionarioPJ(2, "Bia", "11144477735", 750, 3);
        assertEquals(2250, f.calcularRemuneracao()); assertEquals(2250, f.calcularRemuneracaoLiquida());
        assertEquals(2, f.getIdentificador());
    }
    @Test void rejeitaValoresEQuantidadesInvalidas() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioPJ(1, "Bia", "11144477735", -1, 1));
        FuncionarioPJ f = new FuncionarioPJ(1, "Bia", "11144477735", 1, 1);
        assertThrows(IllegalArgumentException.class, () -> f.setQuantidadeProjetos(-1));
        assertThrows(IllegalArgumentException.class, () -> f.setValorPorProjeto(Double.NaN));
    }
}
