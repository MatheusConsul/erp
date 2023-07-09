package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.banco_dados.ApiBD;
import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_caixa.Pedido;
import com.loja_do_fulano.setor_estoque.Entrega;
import com.loja_do_fulano.setor_gestao.GestaoEstoque;
import com.loja_do_fulano.setor_vendas.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaAcompanharVendasController {

@FXML
    private Button btnAtualizarLista;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Pedido, String> colunaCPF;

    @FXML
    private TableColumn<Item, Integer> colunaCodigo;

    @FXML
    private TableColumn<Item, String> colunaDescricao;

    @FXML
    private TableColumn<Pedido, String> colunaNome;

    @FXML
    private TableColumn<Pedido, Integer> colunaPedido;

    @FXML
    private TableColumn<Item, Integer> colunaQtd;

    @FXML
    private TableColumn<Pedido, String> colunaStatusPedido;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private TableView<Pedido> tbTodosPedidos;

    @FXML
    private TableView<Item> tbProdutosEntrega;

    private static String usuarioLogado = null;
    public ObservableList<Item> observableListItens;
    public ObservableList<Pedido> observableListPedidos;

    @FXML
    void acaoSelecionarItem(MouseEvent event){
        
        if (event.getClickCount() == 1) {
            Pedido pedidoSelecionado = tbTodosPedidos.getSelectionModel().getSelectedItem();
            if (pedidoSelecionado != null) {
                atualizarListaItens(pedidoSelecionado);
            }else{
                System.out.println("Nenhum pedido foi selecionado");
            }
        }
    }

    @FXML
    void acaoAtualizarLista(ActionEvent event) {
        atualizarListaPedidos();
    }

    @FXML
    void acaoSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnAtualizarLista.getScene().getWindow();
        App.telaLogin(stage);
    }

    @FXML
    void acaoVoltar(ActionEvent event) throws IOException {
        TelaEstoqueController.setUsuarioLogado(usuarioLogado); 
        App.setRoot("telaGerente");
    }



    @FXML
    private void initialize() throws IOException {
        
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);

        colunaPedido.setCellValueFactory(data -> data.getValue().numPedidoProperty().asObject());
        
        colunaCPF.setCellValueFactory(data -> data.getValue().cpfProperty());
        
        colunaNome.setCellValueFactory(data -> data.getValue().nomeProperty()); 

        colunaStatusPedido.setCellValueFactory(data -> data.getValue().statusPedidoProperty());

        colunaCodigo.setCellValueFactory(data -> data.getValue().codigoProperty().asObject());

        colunaDescricao.setCellValueFactory(data -> data.getValue().descricaoProperty());

        colunaQtd.setCellValueFactory(data -> data.getValue().getQtdDoItemProperty().asObject());

        atualizarListaPedidos();
        
    }

    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }

    private void atualizarListaPedidos(){

        observableListPedidos = FXCollections.observableArrayList(GestaoEstoque.getListaPedidos());

        tbTodosPedidos.setItems(observableListPedidos);

    }

    private void atualizarListaItens(Pedido pedido){

        observableListItens = FXCollections.observableArrayList(GestaoEstoque.getListaItens(pedido));

        tbProdutosEntrega.setItems(observableListItens);

    }




    
}
