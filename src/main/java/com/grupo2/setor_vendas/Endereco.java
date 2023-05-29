package com.grupo2.setor_vendas;

public class Endereco {

    private String rua;
    private String complemento;
    private String cidade;
    private int numero_casa;
    private long cep;

    public void endereco (String rua, String complemento, String cidade, int numero_casa, long cep){
        this.rua = rua;
        this.complemento = complemento;
        this.cidade = cidade;
        this.numero_casa = numero_casa;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getCidade() {
        return cidade;
    }
    public int getNumero_casa() {
        return numero_casa;
    }
    public long getCep() {
        return cep;
    }
    
}
