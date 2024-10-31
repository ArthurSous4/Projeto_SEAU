package br.com.uninassau.ProjetoSEAU;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

@SpringBootApplication
public class ProjetoSeauApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSeauApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite um filme para busca: ");
		var busca = leitura.nextLine();

		String endereco = "http://www.omdbapi.com/?t=" + busca + "&apikey=700745a5";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(endereco))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
	}
}
