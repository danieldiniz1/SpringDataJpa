package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){

        while (system){
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionarios por nome");
            System.out.println("2 - Buscar funcionarios por cpf");
            System.out.println("3 - Buscar funcionarios por salario maior que:");
            System.out.println("4 - Buscar funcionarios por data de contratação maior que:");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioCpf(scanner);
                    break;
                case 3:
                    buscarFuncionarioSalarioMaiorQue(scanner);
                    break;
                case 4:
                    buscarFuncionarioDataContratacaoMaiorQue(scanner);
                    break;
                default:
                    system=false;
                    break;
            }

        }

    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String nomeString = scanner.next();

        List<Funcionario> funcionarioList = funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.nome(nomeString)));
        funcionarioList.forEach(System.out::println);
    }


    private void buscaFuncionarioCpf(Scanner scanner){
        System.out.println("Digite o cpf: ");
        String cpf = scanner.next();

        List<Funcionario> funcionarioList = funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.cpf(cpf)));
        funcionarioList.forEach(System.out::println);
    }

    private void buscarFuncionarioSalarioMaiorQue(Scanner scanner){
        System.out.println("Digite o valor mínimo do salário:");
        String stringSalario = scanner.next();

        List<Funcionario> funcionarioList = funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.salario(new BigDecimal(stringSalario))));
        funcionarioList.forEach(System.out::println);

    }

    private void buscarFuncionarioDataContratacaoMaiorQue(Scanner scanner) {
        System.out.println("Digite a data de inicio do funcionário: (formato dd/MM/yyyy): ");
        String data = scanner.next();
        LocalDate dataCadastro = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarioList = funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.dataContrtacao(dataCadastro)));
        funcionarioList.forEach(System.out::println);
    }
}
