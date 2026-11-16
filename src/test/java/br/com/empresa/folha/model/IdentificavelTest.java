package br.com.empresa.folha.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class IdentificavelTest {
    @Test void classesConcretasRetornamCodigoPolimorficamente() {
        Identificavel[] itens = { new FuncionarioCLT(1,"A","52998224725",1,0), new FuncionarioPJ(2,"B","11144477735",1,1), new FuncionarioComissionado(3,"C","12345678909",1,0,1) };
        assertArrayEquals(new int[]{1,2,3}, java.util.Arrays.stream(itens).mapToInt(Identificavel::getIdentificador).toArray());
    }
}
