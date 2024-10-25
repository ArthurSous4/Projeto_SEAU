package br.com.uninassau.ProjetoSEAU.principal;

import br.com.uninassau.ProjetoSEAU.service.ContaService;
import br.com.uninassau.ProjetoSEAU.modelos.Conta;
import br.com.uninassau.ProjetoSEAU.modelos.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan("br.com.uninassau.ProjetoSEAU")
public class ProjetoSeauApplication implements CommandLineRunner {

	@Autowired
	private ContaService contaService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSeauApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Cadastro");
			System.out.println("2 - Login");
			System.out.println("3 - Listar contas"); // Nova opção
			System.out.println("0 - Sair");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (opcao) {
				case 1: // Cadastro
					String cpfCadastro = "";
					while (true) {
						System.out.print("Digite o CPF (11 dígitos): ");
						cpfCadastro = scanner.nextLine();

						// Verifica se o CPF tem exatamente 11 dígitos
						if (cpfCadastro.matches("\\d{11}")) {
							break; // Sai do loop se a entrada for válida
						} else {
							System.out.println("Erro: O CPF deve ter exatamente 11 dígitos.");
						}
					}

					String senhaCadastro = "";
					while (true) {
						System.out.print("Digite a senha (mínimo 8 caracteres): ");
						senhaCadastro = scanner.nextLine();
						if (senhaCadastro.length() >= 8) {
							break; // Sai do loop se a entrada for válida
						} else {
							System.out.println("Erro: A senha deve ter pelo menos 8 caracteres.");
						}
					}

					Conta conta = new Conta(cpfCadastro, senhaCadastro);
					boolean cadastrado = contaService.cadastrarConta(conta);
					if (cadastrado) {
						System.out.println("Conta cadastrada com sucesso!");
					} else {
						System.out.println("Erro: CPF já cadastrado.");
					}
					break;

				case 2: // Login
					String cpfLogin = "";
					String senhaLogin = "";

					while (true) {
						System.out.print("Digite o CPF: ");
						cpfLogin = scanner.nextLine();
						System.out.print("Digite a senha: ");
						senhaLogin = scanner.nextLine();

						// Autentica o usuário
						if (contaService.autenticar(new Login(cpfLogin, senhaLogin))) {
							System.out.println("Login realizado com sucesso!");
							break; // Sai do loop se a autenticação for bem-sucedida
						} else {
							System.out.println("Erro: CPF ou senha incorretos. Tente novamente.");
						}
					}
					break;

				case 3: // Listar contas
					List<Conta> contas = contaService.listarContas();
					if (contas.isEmpty()) {
						System.out.println("Nenhuma conta cadastrada.");
					} else {
						System.out.println("Contas cadastradas:");
						for (Conta c : contas) {
							System.out.println("CPF: " + c.getCpf() + ", Senha: " + c.getSenha()); // Lembre-se que exibir senhas em texto claro não é uma boa prática
						}
					}
					break;

				case 0:
					System.out.println("Saindo...");
					break;

				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
			}
		} while (opcao != 0);

		scanner.close();
	}
}
