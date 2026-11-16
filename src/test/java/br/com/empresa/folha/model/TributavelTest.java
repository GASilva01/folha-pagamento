package br.com.empresa.folha.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TributavelTest {
    @Test void categoriasTributaveisCalculamDesconto() {
        Tributavel clt = new FuncionarioCLT(1,"A","52998224725",1000,0);
        Tributavel comissionado = new FuncionarioComissionado(2,"B","11144477735",1000,.1,1000);
        assertEquals(80, clt.calcularTributos()); assertEquals(121, comissionado.calcularTributos());
    }
}
