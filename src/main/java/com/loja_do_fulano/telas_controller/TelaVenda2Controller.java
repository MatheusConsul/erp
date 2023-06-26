package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_vendas.Carrinho;
import com.loja_do_fulano.setor_vendas.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaVenda2Controller {

    private static String usuarioLogado = null;
    public ObservableList<Item> observableListCarrinho;

    @FXML
    private Button btnAlterarQuant;

    @FXML
    private Button btnExcluirItem;

    @FXML
    private Button btnFinalizarVenda;

    @FXML
    private Button btnNovoCliente;

    @FXML
    private Button btnPesquisarCPF;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnSolicitarDesconto;

    @FXML
    private Button btnVoltar;

    @FXML
    private CheckBox cbxCartao;

    @FXML
    private CheckBox cbxDinheiro;

    @FXML
    private CheckBox cbxPix;

    @FXML
    private Label lblDesconto;

    @FXML
    private Label lblSubTotal;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private Label lblValorTotal;

    @FXML
    private TableView<Item> tbCarrinho;

    @FXML
    private TableColumn<Item, String> tbColunaDescricaoCarrinho;

    @FXML
    private TableColumn<Item, Float> tbColunaPrecoCarrinho;

    @FXML
    private TableColumn<Item, Integer> tbColunaQuantidadeCarrinho;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCPFpesquisa;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtDataNascimento;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNumCasa;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtTelefone;

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
                    int novaQuant = Integer.parseInt(input);
                    System.out.println("Número digitado: " + novaQuant);
                    if(tbCarrinho.getSelectionModel().getSelectedItem() != null){
                        if(novaQuant > 0 && novaQuant < tbCarrinho.getSelectionModel().getSelectedItem().getQtdDisponivelVenda()){
                            Carrinho.alterarQuantidade(tbCarrinho.getSelectionModel().getSelectedItem(),novaQuant);
                        }else{
                            System.out.println("Quantidade digita inferior a 1 ou superior ao Estoque disponivel");
                        }

                    }else{
                        System.out.println("Nenhum item selecionado para excluir");
                    }
                    atualizarCarrinho();
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Não é um número válido.");
                }
            } else if (response == buttonTypeNao) {
                System.out.println("Nenhum quantidade foi alterada.");
                
            }
        });
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

    @FXML
    void acaoFinalizarVenda(ActionEvent event) {

    }

    @FXML
    void acaoNovoCliente(ActionEvent event) {

        txtNome.setEditable(true);txtNome.setText("");
        txtCPF.setEditable(true);txtCPF.setText("");
        txtDataNascimento.setEditable(true);txtDataNascimento.setText("");
        txtRua.setEditable(true);txtRua.setText("");
        txtBairro.setEditable(true);txtBairro.setText("");
        txtNumCasa.setEditable(true);txtNumCasa.setText("");
        txtCEP.setEditable(true);txtCEP.setText("");
        txtCidade.setEditable(true);txtCidade.setText("");
        txtEstado.setEditable(true);txtEstado.setText("");
        txtTelefone.setEditable(true);txtTelefone.setText("");
        txtEmail.setEditable(true);txtEmail.setText("");

    }

    @FXML
    void acaoPesquisarCPFEnter(KeyEvent event) {
        acaoPesquisarCPF(null);
    }

    @FXML
    void acaoSair(ActionEvent event) throws IOException {
        Stage stage = (Stage)btnSair.getScene().getWindow();
        App.telaLogin(stage);

    }

    @FXML
    void acaoSolicitarDesconto(ActionEvent event) {

    }

    @FXML
    void acaoVoltar(ActionEvent event) throws IOException {
        TelaVenda1Controller.setUsuarioLogado(usuarioLogado); 
        App.setRoot("telaVenda1");
    }

    @FXML
    void acaoPesquisarCPF(ActionEvent event) {

    }

    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }

    private void atualizarCarrinho(){

        observableListCarrinho = FXCollections.observableArrayList(Carrinho.getListaItens());

        tbCarrinho.setItems(observableListCarrinho);
        
        lblSubTotal.setText(Carrinho.getValorTotalCarrinho());

    }

    @FXML
    private void initialize() throws IOException {
        
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);

        tbColunaDescricaoCarrinho.setCellValueFactory(data -> data.getValue().descricaoProperty());

        tbColunaPrecoCarrinho.setCellValueFactory(data -> data.getValue().getPrecoTotalProperty().asObject());

        tbColunaQuantidadeCarrinho.setCellValueFactory(data -> data.getValue().getQtdDoItemProperty().asObject());

        txtNome.setEditable(false);
        txtCPF.setEditable(false);
        txtDataNascimento.setEditable(false);
        txtRua.setEditable(false);
        txtBairro.setEditable(false);
        txtNumCasa.setEditable(false);
        txtCEP.setEditable(false);
        txtCidade.setEditable(false);
        txtEstado.setEditable(false);
        txtTelefone.setEditable(false);
        txtEmail.setEditable(false);

        atualizarCarrinho();
        
    }

}
