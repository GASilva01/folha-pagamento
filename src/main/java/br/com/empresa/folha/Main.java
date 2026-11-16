package br.com.empresa.folha;

import br.com.empresa.folha.model.FuncionarioCLT;
import br.com.empresa.folha.repository.FuncionarioRepository;
import br.com.empresa.folha.service.FuncionarioService;
import br.com.empresa.folha.view.FuncionarioView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService(new FuncionarioRepository());
        service.cadastrarFuncionario(new FuncionarioCLT(1, "Ana Silva", "529.982.247-25", 3500.00, 450.00));
        new FuncionarioView(service, new Scanner(System.in)).iniciar();
    }
}
