package br.com.uninassau.ProjetoSEAU.modelos;

public class Cadastro {
    private Conta conta;

    public Cadastro(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
}