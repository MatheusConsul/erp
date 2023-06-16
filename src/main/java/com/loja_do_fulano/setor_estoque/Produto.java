package com.loja_do_fulano.setor_estoque;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {
    private final SimpleIntegerProperty codigo;
    private final SimpleStringProperty descricao;
    private final SimpleStringProperty quantidade;
    private final SimpleStringProperty preco;

    public Produto(int codigo, String descricao, String quantidade, String preco) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.quantidade = new SimpleStringProperty(quantidade);
        this.preco = new SimpleStringProperty(preco);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public SimpleIntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
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

    public String getQuantidade() {
        return quantidade.get();
    }

    public SimpleStringProperty quantidadeProperty() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade.set(quantidade);
    }

    public String getPreco() {
        return preco.get();
    }

    public SimpleStringProperty precoProperty() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco.set(preco);
    }
}
