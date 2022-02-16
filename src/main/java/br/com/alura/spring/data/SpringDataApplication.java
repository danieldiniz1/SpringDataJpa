package br.com.alura.spring.data;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService crudFuncionarioService){
		this.crudCargoService = cargoService;
		this.crudFuncionarioService = crudFuncionarioService;
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
			System.out.println("");

			int action = scanner.nextInt();

			if(action == 1){
				crudCargoService.inicial(scanner);
			} else if (action == 2){
				crudFuncionarioService.inicial(scanner);
			} else  {
				system = false;
			}

		}


	}
}
