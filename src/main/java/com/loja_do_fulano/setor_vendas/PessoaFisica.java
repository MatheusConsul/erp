package com.loja_do_fulano.setor_vendas;

public class PessoaFisica {

    private String nome;
    private long cpf;
    private String data_nascimento;
    private int telefone;
    private String email;
    private Endereco endereco;

    public PessoaFisica(String nome, long cpf,String data_nascimento,int telefone,String email, Endereco endereco){

        this.nome = nome;
        this.cpf  = cpf;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.email = email; 
        this.endereco = endereco;
    }

    public String getEmail(){
        return email;
    }

    public long getCPF(){
        return cpf;
    }

    public String getData_Nascimento(){
        return data_nascimento;
    }

    public int getTelefone(){
        return telefone;
    }

    public String getNome(){
        return nome;
    }

    public String getRua(){
        return endereco.getRua();
    }

    public String getBairro(){
        return endereco.getBairro();
    }
    public String getCidade(){
        return endereco.getCidade();
    }
    public int getNumCasa(){
        return endereco.getNumero_casa();
    }
    public String getCep(){
        return endereco.getCep();
    }
    public String getEstado(){
        return endereco.getEstado();
    }

}
