package br.com.empresa.folha.view;
import br.com.empresa.folha.repository.FuncionarioRepository;
import br.com.empresa.folha.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
class FuncionarioViewTest {
    @Test void menuEncerraComOpcaoZero() {
        FuncionarioView view = new FuncionarioView(new FuncionarioService(new FuncionarioRepository()), new Scanner(new ByteArrayInputStream("0\n".getBytes(StandardCharsets.UTF_8))));
        assertDoesNotThrow(view::iniciar);
    }
}
