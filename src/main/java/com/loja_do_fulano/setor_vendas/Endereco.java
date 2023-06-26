package com.loja_do_fulano.setor_vendas;

public class Endereco {

    private String rua;
    private String bairro;
    private String cidade;
    private int numero_casa;
    private String cep;
    private String estado;

    public Endereco(String rua, String bairro, String cidade, int numero_casa, String cep, String estado){

        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero_casa = numero_casa;
        this.cep = cep;
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }
    public String getBairro() {
        return bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public int getNumero_casa() {
        return numero_casa;
    }
    public String getCep() {
        return cep;
    }
    public String getEstado() {
        return estado;
    }
}
