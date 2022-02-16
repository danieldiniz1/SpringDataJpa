package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository repository;

    public CrudCargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação de cargo deseja executar?");
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
                    atualizar(scanner);
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

    private void salvar(Scanner scanner){
        System.out.println("Descrição do Cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo(descricao);
        repository.save(cargo);
        System.out.println("O cargo : " + cargo.getDescricao() + " foi criado com sucesso!");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Qual o ID que deseja atualizar?");
        int id = scanner.nextInt();
        System.out.println("Qual a nova descrição do Cargo?");
        String descricao = scanner.next();

        Cargo cargo = new Cargo(id,descricao);
        repository.save(cargo);
        System.out.println("O cargo de id : " + id + " foi atualizado com a nova descrição: " + descricao);
    }

    private void excluir(Scanner scanner){
        System.out.println("Qual ID deseja excluir?");
        int id = scanner.nextInt();
        repository.deleteById(id);
    }

    private void visualizar() {
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }
}
