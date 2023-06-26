package com.loja_do_fulano.setor_vendas;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    
    private final SimpleIntegerProperty codigo;
    private final SimpleStringProperty descricao;
    private final SimpleIntegerProperty qtd_do_item;
    private final SimpleIntegerProperty qtd_disponivel_venda;
    private final SimpleFloatProperty precoUnitario;
    private final SimpleFloatProperty precoTotal;

    public Item(int codigo, String descricao, int qtd_do_item, int  qtd_disponivel_venda, float precoUnitario) {

        this.codigo = new SimpleIntegerProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.qtd_do_item = new SimpleIntegerProperty(qtd_do_item);
        this.qtd_disponivel_venda = new SimpleIntegerProperty(qtd_disponivel_venda);
        this.precoUnitario = new SimpleFloatProperty(precoUnitario);
        this.precoTotal = new SimpleFloatProperty(qtd_do_item * precoUnitario);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public SimpleIntegerProperty codigoProperty() {
        return codigo;
    }

    public String getDescricao() {
        return descricao.get();
    }

    public SimpleStringProperty descricaoProperty() {
        return descricao;
    }

    public int getQtdDoItem() {
        return qtd_do_item.get();
    }

    public SimpleIntegerProperty getQtdDoItemProperty() {
        return qtd_do_item;
    }

    public void setQtdDoItem(int qtd_do_item) {
        this.qtd_do_item.set(qtd_do_item);
        this.precoTotal.set(qtd_do_item * precoUnitario.get());
    }

    public int getQtdDisponivelVenda() {
        return qtd_disponivel_venda.get();
    }

    public float getPrecoUnitario() {
        return precoUnitario.get();
    }

    public SimpleFloatProperty getPrecoUnitarioProperty() {
        return precoUnitario;
    }

    public float getPrecoTotal() {
        return precoTotal.get();
    }

    public SimpleFloatProperty getPrecoTotalProperty() {
        return precoTotal;
    }

    /*private void setPrecoTotal(float precoTotal) {
        this.precoTotal.set(precoTotal);
    }*/

}
