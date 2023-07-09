package com.loja_do_fulano.setor_vendas;

import java.io.Serializable;

public class ItemSerializable implements Serializable {

    private int codigo;
    private String descricao;
    private int qtd_do_item;
    private int qtd_disponivel_venda;
    private float precoUnitario;
    private float precoTotal;

    public ItemSerializable(int codigo, String descricao, int qtd_do_item, int  qtd_disponivel_venda, float precoUnitario) {

        this.codigo = codigo;
        this.descricao = descricao;
        this.qtd_do_item = qtd_do_item;
        this.qtd_disponivel_venda = qtd_disponivel_venda;
        this.precoUnitario = precoUnitario;
        this.precoTotal = qtd_do_item * precoUnitario;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQtdDoItem() {
        return qtd_do_item;
    }

    public void setQtdDoItem(int qtd_do_item) {
        this.qtd_do_item = qtd_do_item;
        this.precoTotal = qtd_do_item * precoUnitario;
    }

    public int getQtdDisponivelVenda() {
        return qtd_disponivel_venda;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    
}
