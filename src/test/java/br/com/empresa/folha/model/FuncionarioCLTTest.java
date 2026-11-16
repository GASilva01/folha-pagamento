package br.com.empresa.folha.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioCLTTest {
    @Test void calculaBrutoTributosELiquido() {
        Funcionario f = new FuncionarioCLT(1, "Ana", "529.982.247-25", 1000, 200);
        assertEquals(1200, f.calcularRemuneracao()); assertEquals(96, ((Tributavel) f).calcularTributos());
        assertEquals(1104, f.calcularRemuneracaoLiquida()); assertEquals(1, ((Identificavel) f).getIdentificador());
    }
    @Test void rejeitaCpfNomeEValoresInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, "Ana", "123", 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, " ", "52998224725", 1, 0));
        FuncionarioCLT f = new FuncionarioCLT(1, "Ana", "52998224725", 1, 0);
        assertThrows(IllegalArgumentException.class, () -> f.setBeneficio(-1));
        assertThrows(IllegalArgumentException.class, () -> f.setSalarioBase(-1));
    }
}
