// Pacote: br.com.uninassau.projetoseau.model
package br.com.uninassau.ProjetoSEAU.modelos;

public class Conta {
    private String cpf;
    private String senha;

    public Conta(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
