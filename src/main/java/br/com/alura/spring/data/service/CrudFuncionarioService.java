package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private FuncionarioRepository repository;

    public CrudFuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void inicial (Scanner scanner){
        while (system){
            System.out.println("Qual ação de Funcionario deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Listar");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizarNome(scanner);
                    break;
                case 3:
                    excluir(scanner);
                    break;
                case 4:
                    visualizar();
                    break;
                default:
                    system = false;
                    break;
        }
    }

}

    private void visualizar() {
        Iterable<Funcionario> funcionarios = repository.findAll();
        System.out.println("Segue a lista de todos os funcionarios");
        funcionarios.forEach(funcionario -> System.out.println(funcionario +" "+  funcionario.getCargo().getDescricao()));
    }

    private void excluir(Scanner scanner) {
        System.out.println("Qual o id do funcionário que deseja excluir? ");
        int id = scanner.nextInt();
        repository.deleteById(id);
        System.out.println("Funcionario excluido do sistema");
    }

    private void atualizarNome(Scanner scanner) {
        System.out.println("Qual o id do funcionário que deseja excluir? ");
        int id = scanner.nextInt();
        System.out.println("Qual o novo nome do funcionário?");
        String nome = scanner.next();

        Funcionario funcionario = new Funcionario(id, nome);
        repository.save(funcionario);
    }

    private void salvar(Scanner scanner) {
        System.out.println("Funcionário: ");
        String nome = scanner.next();
        System.out.println("CPF: ");
        String cpf = scanner.next();
        System.out.println("Salario");
        String salario = scanner.next();
//        System.out.println("Cargo");
//        String cargo = scanner.next();


        Funcionario funcionario = new Funcionario(nome,cpf,new BigDecimal(salario));

        repository.save(funcionario);

    }
    }
