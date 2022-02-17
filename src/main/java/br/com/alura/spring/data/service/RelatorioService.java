package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private Boolean system = true;

    private FuncionarioRepository funcionarioRepository;

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionario por nome");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscaFuncionarioPorNome(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioPorNome(Scanner scanner){
        System.out.println("Qual o nome do funcionario que deseja informações: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);

        if(!funcionarios.isEmpty()){
            funcionarios.forEach(System.out::println);
        } else {
            System.out.println("Não existe um funcionário com o nome: " + nome);
        }
    }
}
