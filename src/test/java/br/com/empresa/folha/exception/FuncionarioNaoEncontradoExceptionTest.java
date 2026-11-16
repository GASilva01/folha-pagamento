package br.com.empresa.folha.exception;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FuncionarioNaoEncontradoExceptionTest {
    @Test void preservaMensagem() { assertEquals("ausente", new FuncionarioNaoEncontradoException("ausente").getMessage()); }
}
