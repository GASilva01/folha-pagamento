package br.com.empresa.folha.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioComissionadoTest {
    @Test void calculaSalarioComComissaoEDesconto() {
        FuncionarioComissionado f = new FuncionarioComissionado(3, "Caio", "11144477735", 2000, .1, 5000);
        assertEquals(2500, f.calcularRemuneracao()); assertEquals(275, f.calcularTributos());
        assertEquals(2225, f.calcularRemuneracaoLiquida());
    }
    @Test void rejeitaPercentualEValoresInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioComissionado(1, "Caio", "11144477735", 1, 1.1, 1));
        FuncionarioComissionado f = new FuncionarioComissionado(1, "Caio", "11144477735", 1, 0, 1);
        assertThrows(IllegalArgumentException.class, () -> f.setPercentualComissao(-.01));
        assertThrows(IllegalArgumentException.class, () -> f.setVendas(-1));
    }
}
