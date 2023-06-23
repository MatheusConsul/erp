package com.loja_do_fulano.telas_controller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.loja_do_fulano.banco_dados.ApiBD;
import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_estoque.Produto;
import com.loja_do_fulano.setor_vendas.Carrinho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaVenda1Controller {

    private static String usuarioLogado = null;

    //======================================

     @FXML
    private Button btnAdicionarCarrinho;

    @FXML
    private Button btnAlterarQuant;

    @FXML
    private Button btnContinuarVenda;

    @FXML
    private Button btnExcluirItem;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnSair;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private Label lblValorTotal;

    @FXML
    private TableView<Produto> tbCarrinho;

    @FXML
    private TableColumn<Produto, String> tbColunaDescricaoCarrinho;

    @FXML
    private TableColumn<Produto, Float> tbColunaPrecoCarrinho;

    @FXML
    private TableColumn<Produto, Integer> tbColunaQuantidadeCarrinho;

    @FXML
    private TextField txtCampoPesquisa;

    @FXML
    void acaoAdicionarCarrinho(ActionEvent event) {

        Carrinho.addItem(tbProdutos.getSelectionModel().getSelectedItem());
        atualizarCarrinho();
    }

    @FXML
    void acaoAlterarQuant(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alterar quantidade do produto");
        alert.setHeaderText("Você realmente deseja alterar a quantidade do produto?");
        alert.setContentText("Digite a nova quantidade:");

        // Cria um campo de texto
        TextField textField = new TextField();
        textField.setPromptText("Digite aqui a nova quantidade");

        // Adiciona o campo de texto ao layout da janela de diálogo
        GridPane grid = new GridPane();
        grid.add(textField, 0, 0);
        alert.getDialogPane().setContent(grid);

        // Personalize os botões do diálogo
        ButtonType buttonTypeSim = new ButtonType("Sim");
        ButtonType buttonTypeNao = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

        // Mostra o diálogo e espera pela resposta do usuário
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeSim) {
                String input = textField.getText();
                try {
                    int numero = Integer.parseInt(input);
                    System.out.println("Número digitado: " + numero);
                    // Faça algo com o número digitado pelo usuário
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Não é um número válido.");
                }
            } else if (response == buttonTypeNao) {
                System.out.println("Usuário escolheu Não.");
                // Faça algo se o usuário escolher "Não"
            }
        });
    }


    @FXML
    void acaoContinuarVenda(ActionEvent event) {

    }

    @FXML
    void acaoExcluirItem(ActionEvent event) {
        if(tbCarrinho.getSelectionModel().getSelectedItem() != null){
            Carrinho.excluirItem(tbCarrinho.getSelectionModel().getSelectedItem());
        }else{
            System.out.println("Nenhum item selecionado para excluir");
        }
        atualizarCarrinho();
    }

    


    //=======================================



    @FXML
    private TableColumn<Produto, Integer> tbColunaCodigo;

    @FXML
    private TableColumn<Produto, String> tbColunaDescricao;

    @FXML
    private TableColumn<Produto, Float> tbColunaPreco;

    @FXML
    private TableColumn<Produto, Integer> tbColunaQuantidade;

    @FXML
    private TableView<Produto> tbProdutos;

    public ObservableList<Produto> observableListProdutos;
    public ObservableList<Produto> observableListCarrinho;

    @FXML
    void acaoPesquisarEnter(KeyEvent event) throws IOException{
        
        if (event.getCode() == KeyCode.ENTER) {
            pesquisarProduto();
        } 
    }

    @FXML
    private void pesquisarProduto() throws IOException { 
        
        System.out.println("\n\n\n PESUISANDO \n\n");

        observableListProdutos = FXCollections.observableArrayList(ApiBD.buscaProdutos(txtCampoPesquisa.getText()));

        tbProdutos.setItems(observableListProdutos);
                
    }

    @FXML
    void acaoSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnPesquisar.getScene().getWindow();
        App.telaLogin(stage);
        
    }


    @FXML
    private void initialize() throws IOException {
        
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++

        tbColunaCodigo.setCellValueFactory(data -> data.getValue().codigoProperty().asObject());

        tbColunaDescricao.setCellValueFactory(data -> data.getValue().descricaoProperty());
        
        tbColunaQuantidade.setCellValueFactory(data -> data.getValue().quantidadeProperty().asObject());
        
        tbColunaPreco.setCellValueFactory(data -> data.getValue().precoProperty().asObject()); 

        //=================================

        tbColunaDescricaoCarrinho.setCellValueFactory(data -> data.getValue().descricaoProperty());

        tbColunaPrecoCarrinho.setCellValueFactory(data -> data.getValue().precoProperty().asObject());

        tbColunaQuantidadeCarrinho.setCellValueFactory(data -> data.getValue().quantidadeProperty().asObject());
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }

    private void atualizarCarrinho(){

        observableListCarrinho = FXCollections.observableArrayList(Carrinho.getListaProdutos());

        tbCarrinho.setItems(observableListCarrinho);
        
        lblValorTotal.setText(Carrinho.getValorTotalCarrinho());

    }
    
    
}
