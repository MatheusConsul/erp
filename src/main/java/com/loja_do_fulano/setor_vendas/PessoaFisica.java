package com.loja_do_fulano.setor_vendas;

public class PessoaFisica {

    private String nome;
    private int cpf;
    private int data_nascimento;
    private int contato;
    private String email;
    private Endereco endereco;

    public void executar(String nome, int cpf,int data_nascimento,int contato,String email){
        this.nome = nome;
        this.cpf  = cpf;
        this.data_nascimento = data_nascimento;
        this.contato = contato;
        this.email = email; 
    }

    public String getEmail(){
        return email;
    }

    public int getCPF(){
        return cpf;
    }

    public int getData_Nascimento(){
        return data_nascimento;
    }

    public int getContato(){
        return contato;
    }

    public String getNome(){
        return nome;
    }

}
