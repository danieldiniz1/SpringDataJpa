package br.com.alura.spring.data;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatorioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final RelatorioService relatorioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService crudFuncionarioService, RelatorioService relatorioService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService){
		this.crudCargoService = cargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.relatorioService = relatorioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system){
			System.out.println("Qual ação você deseja executar:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidades de Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("");

			int action = scanner.nextInt();

			switch (action){
				case 1:
					crudCargoService.inicial(scanner);
					break;
				case 2:
					crudFuncionarioService.inicial(scanner);
					break;
				case 3:
					crudUnidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatorioService.inicial(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
	}
}
