package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.setor_caixa.Caixa;
import com.loja_do_fulano.setor_caixa.Pedido;
import com.loja_do_fulano.setor_estoque.Produto;
import com.loja_do_fulano.setor_vendas.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TelaEntregaController {

     @FXML
    private Button btnAtualizarLista;

    @FXML
    private Button btnRealizarEntrega;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Pedido, String> colunaCPF;

    @FXML
    private TableColumn<Produto, Integer> colunaCodigo;

    @FXML
    private TableColumn<Produto, String> colunaDescricao;

    @FXML
    private TableColumn<Pedido, String> colunaNome;

    @FXML
    private TableColumn<Pedido, Integer> colunaPedido;

    @FXML
    private TableColumn<Produto, Integer> colunaQtd;

    @FXML
    private TableColumn<Pedido, String> colunaStatusPedido;

    @FXML
    private AnchorPane lblAviso;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private TableView<Pedido> tbPedidosAprovados;

    @FXML
    private TableView<Produto> tbProdutosEntrega;

    private static String usuarioLogado = null;
    public ObservableList<Produto> observableListProdutos;
    public ObservableList<Item> observableListPedidos;

    @FXML
    void acaoAtualizarLista(ActionEvent event) {

    }

    @FXML
    void acaoRealizarEntrega(ActionEvent event) {

    }

    @FXML
    void acaoSair(ActionEvent event) {

    }

    @FXML
    void acaoVoltar(ActionEvent event) {

    }



    @FXML
    private void initialize() throws IOException {
        
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);

        colunaPedido.setCellValueFactory(data -> data.getValue().numPedidoProperty().asObject());
        
        colunaCPF.setCellValueFactory(data -> data.getValue().cpfProperty());
        
        colunaNome.setCellValueFactory(data -> data.getValue().nomeProperty()); 

        colunaStatusPedido.setCellValueFactory(data -> data.getValue().statusPedidoProperty());

        colunaCodigo.setCellValueFactory(data -> data.getValue().co().asObject());

        colunaTipoPag.setCellValueFactory(data -> data.getValue().tipoPagProperty());

        colunaTotal.setCellValueFactory(data -> data.getValue().totalProperty());

        atualizarListaPedidos();
        
    }

    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }

    private void atualizarListaPedidos(){

        observableListPedidos = FXCollections.observableArrayList(Caixa.getListaPedidos());

        tbPedidosAprovados.setItems(observableListPedidos);


    }

    
}
