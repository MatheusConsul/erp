package com.loja_do_fulano.setor_estoque;

public class Produto {

    private String codigo;
    private String descricao;
    private String quantidade;
    private String preco;

    public Produto(String codigo, String descricao, String quantidade,String preco){
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getDescricao(){
        return descricao;
    }
    public String getQuantidade(){
        return quantidade;
    }
    public String getPreco(){
        return preco;
    }    
    
}
