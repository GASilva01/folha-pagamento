package br.com.empresa.folha.exception;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FuncionarioJaCadastradoExceptionTest {
    @Test void preservaMensagem() { assertEquals("duplicado", new FuncionarioJaCadastradoException("duplicado").getMessage()); }
}
