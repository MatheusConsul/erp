package com.loja_do_fulano.setor_estoque;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {

    private final SimpleIntegerProperty codigo;
    private final SimpleStringProperty descricao;
    private final SimpleIntegerProperty qtdDisponivelVenda;
    private final SimpleFloatProperty preco;

    public Produto(int codigo, String descricao, int quantidade, Float preco) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.qtdDisponivelVenda = new SimpleIntegerProperty(quantidade);
        this.preco = new SimpleFloatProperty(preco);
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

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public int getQtdDisponivelVenda() {
        return qtdDisponivelVenda.get();
    }

    public SimpleIntegerProperty qtdDisponivelVendaProperty() {
        return qtdDisponivelVenda;
    }

    public void setQtdDisponivelVenda(int quantidade) {
        this.qtdDisponivelVenda.set(quantidade);
    }

    public Float getPreco() {
        return preco.get();
    }

    public SimpleFloatProperty precoProperty() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco.set(preco);
    }
}
