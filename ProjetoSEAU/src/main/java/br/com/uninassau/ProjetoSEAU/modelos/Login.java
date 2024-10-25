package br.com.uninassau.ProjetoSEAU.modelos;

public class Login {
    private String cpf;
    private String senha;

    public Login(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }
}
