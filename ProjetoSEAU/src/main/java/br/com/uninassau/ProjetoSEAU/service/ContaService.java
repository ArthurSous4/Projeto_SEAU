// Pacote: br.com.uninassau.ProjetoSEAU.service
package br.com.uninassau.ProjetoSEAU.service;

import br.com.uninassau.ProjetoSEAU.modelos.Conta;
import br.com.uninassau.ProjetoSEAU.modelos.Login;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ContaService {
    private final List<Conta> contas = new ArrayList<>();

    // Padrão regex para validação de CPF
    private static final String CPF_REGEX = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$";

    public boolean cadastrarConta(Conta conta) {
        if (conta != null && !contaExistente(conta.getCpf()) && validarCpf(conta.getCpf()) && validarSenha(conta.getSenha())) {
            contas.add(conta);
            return true;
        }
        return false;
    }

    public boolean autenticar(Login login) {
        return contas.stream()
                .anyMatch(conta -> conta.getCpf().equals(login.getCpf())
                        && conta.getSenha().equals(login.getSenha()));
    }

    private boolean contaExistente(String cpf) {
        return contas.stream().anyMatch(conta -> conta.getCpf().equals(cpf));
    }

    // Método para validar CPF
    private boolean validarCpf(String cpf) {
        return Pattern.matches(CPF_REGEX, cpf);
    }

    // Método para validar senha
    private boolean validarSenha(String senha) {
        // Exemplo simples: verificar se a senha tem pelo menos 8 caracteres
        return senha.length() >= 8;
    }

    // Método para listar todas as contas
    public List<Conta> listarContas() {
        return new ArrayList<>(contas); // Retorna uma nova lista com as contas cadastradas
    }
}
