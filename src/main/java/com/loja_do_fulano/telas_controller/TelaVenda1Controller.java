package com.loja_do_fulano.telas_controller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_estoque.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaVenda1Controller {

    private static String usuarioLogado = null;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnSair;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private TableColumn<Produto, String> tbColunaCodigo;

    @FXML
    private TableColumn<Produto, String> tbColunaDescricao;

    @FXML
    private TableColumn<Produto, String> tbColunaPreco;

    @FXML
    private TableColumn<Produto, String> tbColunaQuantidade;

    @FXML
    private TableView<Produto> tbProdutos;

    @FXML
    private TextField txtCampoPesquisa;


    private List<Produto> listaProdutos = new ArrayList<>();
    private ObservableList<Produto> observableListProdutos;

    @FXML
    private void pesquisarProduto() throws IOException { 
        
        System.out.println("\n\n\n PESUISANDO \n\n");

        tbColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        
        tbColunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        tbColunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        tbColunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        Produto produto1 = new Produto("1233","Notebook acer preto","20","2000,00");

        Produto produto2 = new Produto("1244","Geladeira Consul Branca","10","3000,00");
        
        listaProdutos.add(produto1);
        listaProdutos.add(produto2);

        observableListProdutos = FXCollections.observableArrayList(listaProdutos);

        tbProdutos.setItems(observableListProdutos);
        
    }

    @FXML
    void acaoSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnPesquisar.getScene().getWindow();
        App.telaLogin(stage);
        
    }

    @FXML
    private void initialize() throws IOException {
        pesquisarProduto();
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);
    }

    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }
    
    
}
