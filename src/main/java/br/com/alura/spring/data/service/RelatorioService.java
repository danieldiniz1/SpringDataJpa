package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FuncionarioRepository funcionarioRepository;

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionario por nome");
            System.out.println("2 - Buscar funcionario por nome, data de contratação e salario");
            System.out.println("3 - Buscar funcionario data de contratação");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscaFuncionarioPorNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
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

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual o nome do funcionario que deseja informações: ");
        String nome = scanner.next();

        System.out.println("Qual a data de contratação: ");
        String data = scanner.next();
        LocalDate date = LocalDate.parse(data, formatter);

        System.out.println("Qual o Salário: ");
        BigDecimal salario = new BigDecimal(scanner.next());

        List<Funcionario> funcionarios = funcionarioRepository.findNomeDataContratacaoTemSalarioMaior(nome,salario,date);
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Qual a data de contratação deseja pesquisar: (dd/MM/yyyy)");
        String data = scanner.next();
        LocalDate date = LocalDate.parse(data,formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(date);
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }
}
