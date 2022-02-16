package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean system = true;
    private UnidadeTrabalhoRepository repository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação de Unidade de Trabalho deseja executar?");
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

    private void salvar(Scanner scanner) {
        System.out.println("Descriciao da unidade de trabalho: ");
        String descricao = scanner.next();
        System.out.println("Endereço: ");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(descricao,endereco);
        repository.save(unidadeTrabalho);
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id da unidade que deseja atualizar: ");
        int id = scanner.nextInt();
        System.out.println("Descriciao da unidade de trabalho: ");
        String descricao = scanner.next();
        System.out.println("Endereço: ");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(id,descricao,endereco);
        repository.save(unidadeTrabalho);
    }

    private void excluir(Scanner scanner) {
        System.out.println("Id da unidade que deseja excluir: ");
        int id = scanner.nextInt();

        repository.deleteById(id);
    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidadesTrabalho = repository.findAll();
        unidadesTrabalho.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));
    }

}
