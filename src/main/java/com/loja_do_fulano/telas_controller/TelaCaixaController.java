package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_caixa.Caixa;
import com.loja_do_fulano.setor_caixa.Pedido;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TelaCaixaController {

    @FXML
    private Button btnAprovar;

    @FXML
    private Button btnAtualizarLista;

    @FXML
    private Button btnReprovar;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<Pedido, String> colunaCPF;

    @FXML
    private TableColumn<Pedido, String> colunaDesconto;

    @FXML
    private TableColumn<Pedido, String> colunaNome;

    @FXML
    private TableColumn<Pedido, Integer> colunaPedido;

    @FXML
    private TableColumn<Pedido, String> colunaStatusPedido;

    @FXML
    private TableColumn<Pedido, String> colunaSubTotal;

    @FXML
    private TableColumn<Pedido, String> colunaTipoPag;

    @FXML
    private TableColumn<Pedido, String> colunaTotal;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private TableView<Pedido> tbPedidosRealizados;

    private static String usuarioLogado = null;
    
    public ObservableList<Pedido> observableListPedidos;

    @FXML
    void acaoAprovarCompra(ActionEvent event) {
        
        Caixa.aprovarCompra(tbPedidosRealizados.getSelectionModel().getSelectedItem());
        atualizarListaPedidos();
    }

    @FXML
    void acaoAtualizarLista(ActionEvent event) {
        atualizarListaPedidos();
    }


    @FXML
    void acaoReprovarCompra(ActionEvent event) {
       Caixa.reprovarCompra(tbPedidosRealizados.getSelectionModel().getSelectedItem());
        atualizarListaPedidos();
    }

    @FXML
    void acaoSair(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnAprovar.getScene().getWindow();
        App.telaLogin(stage);

    }

    @FXML
    private void initialize() throws IOException {
        
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);

        colunaPedido.setCellValueFactory(data -> data.getValue().numPedidoProperty().asObject());
        
        colunaCPF.setCellValueFactory(data -> data.getValue().cpfProperty());

        colunaDesconto.setCellValueFactory(data -> data.getValue().descontoProperty());
        
        colunaNome.setCellValueFactory(data -> data.getValue().nomeProperty()); 

        colunaStatusPedido.setCellValueFactory(data -> data.getValue().statusPedidoProperty());

        colunaSubTotal.setCellValueFactory(data -> data.getValue().subTotalProperty());

        colunaTipoPag.setCellValueFactory(data -> data.getValue().tipoPagProperty());

        colunaTotal.setCellValueFactory(data -> data.getValue().totalProperty());

        atualizarListaPedidos();
        
    }

    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }

    private void atualizarListaPedidos(){

        observableListPedidos = FXCollections.observableArrayList(Caixa.getListaPedidos());

        tbPedidosRealizados.setItems(observableListPedidos);

    }



}
