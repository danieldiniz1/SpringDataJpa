package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private FuncionarioRepository funcionarioRepository;
    private CargoRepository cargoRepository;
    private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
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

    private List<UnidadeTrabalho> unidades(Scanner scanner){
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue){
            System.out.println("UnidadeId: (para sair digite 0");
            int unidadeId = scanner.nextInt();

            if(unidadeId != 0){
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return unidades;
    }


    private void visualizar() {
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        System.out.println("Segue a lista de todos os funcionarios");
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private void excluir(Scanner scanner) {
        System.out.println("Qual o id do funcionário que deseja excluir? ");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Funcionario excluido do sistema");
    }

    private void atualizarNome(Scanner scanner) {
        System.out.println("Qual o id do funcionário que deseja excluir? ");
        int id = scanner.nextInt();
        System.out.println("Qual o novo nome do funcionário?");
        String nome = scanner.next();

        Funcionario funcionario = new Funcionario(id, nome);
        funcionarioRepository.save(funcionario);
        System.out.println("Nome do funcionário " + nome + " atualizado com sucesso!");
    }

    private void salvar(Scanner scanner) {
        System.out.println("Funcionário: ");
        String nome = scanner.next();
        System.out.println("CPF: ");
        String cpf = scanner.next();
        System.out.println("Salario: ");
        String salario = scanner.next();
        System.out.println("CargoId: ");
        Integer cargoId = scanner.nextInt();
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        List<UnidadeTrabalho> unidadesTrabalho = unidades(scanner);


        Funcionario funcionario = new Funcionario(nome,cpf,new BigDecimal(salario),cargo.get());
        funcionario.setUnidadeTrabalho(unidadesTrabalho);

        funcionarioRepository.save(funcionario);
        System.out.println("Novo funcionario: " + nome + " cadastrado com sucesso!");

    }
    }
