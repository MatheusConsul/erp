package com.loja_do_fulano.setor_caixa;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pedido {
    
    private final SimpleIntegerProperty numPedido;
    private final SimpleStringProperty cpf;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty subTotal;
    private final SimpleStringProperty desconto;
    private final SimpleStringProperty total;
    private final SimpleStringProperty tipoPag;
    private final SimpleStringProperty statusPedido;


    public Pedido(int numPedido, String cpf, String nome, String total, String subTotal, String desconto, String statusPedido, String tipoPag) {
        this.numPedido = new SimpleIntegerProperty(numPedido);
        this.cpf = new SimpleStringProperty(cpf);
        this.nome = new SimpleStringProperty(nome);
        this.subTotal = new SimpleStringProperty(subTotal);
        this.desconto = new SimpleStringProperty(desconto);
        this.total = new SimpleStringProperty(total);
        this.tipoPag = new SimpleStringProperty(tipoPag);
        this.statusPedido = new SimpleStringProperty(statusPedido);
    }

    public int getNumPedido() {
        return numPedido.get();
    }

    public SimpleIntegerProperty numPedidoProperty() {
        return numPedido;
    }

    public String getCpf() {
        return cpf.get();
    }

    public SimpleStringProperty cpfProperty() {
        return cpf;
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public String getSubTotal() {
        return subTotal.get();
    }

    public SimpleStringProperty subTotalProperty() {
        return subTotal;
    }

    public String getDesconto() {
        return desconto.get();
    }

    public SimpleStringProperty descontoProperty() {
        return desconto;
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public String getTipoPag() {
        return tipoPag.get();
    }

    public SimpleStringProperty tipoPagProperty() {
        return tipoPag;
    }

    public String getStatusPedido() {
        return statusPedido.get();
    }

    public SimpleStringProperty statusPedidoProperty() {
        return statusPedido;
    }

}
