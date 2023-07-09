package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.banco_dados.ApiBD;
import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_caixa.Pedido;
import com.loja_do_fulano.setor_vendas.Carrinho;
import com.loja_do_fulano.setor_vendas.Endereco;
import com.loja_do_fulano.setor_vendas.Item;
import com.loja_do_fulano.setor_vendas.PessoaFisica;

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
import javafx.scene.input.KeyCode;
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
    void acaoFinalizarVenda(ActionEvent event) throws IOException {

        String cpf = txtCPF.getText();
        cpf = cpf.trim(); // tira os espaços

        if(cpf.isEmpty()){
            System.out.println("Obrigatorio preencher um cliente!!!");
        }else{
            System.out.println("CPF do cliente que vai comprar: " + cpf);
            PessoaFisica cliente = ApiBD.pesquisarClietne(cpf);
            
            if(cliente != null){
                Stage stage = (Stage) btnVoltar.getScene().getWindow();
                Carrinho.finalizarCompra(cliente,stage);

            }else{

                if( txtNumCasa.getText().trim().isEmpty() ||
                    txtRua.getText().trim().isEmpty() ||
                    txtBairro.getText().trim().isEmpty() ||
                    txtCidade.getText().trim().isEmpty() ||
                    txtEstado.getText().trim().isEmpty() ||
                    txtCEP.getText().trim().isEmpty() ||
                    txtNome.getText().trim().isEmpty() ||
                    txtDataNascimento.getText().trim().isEmpty() ||
                    txtTelefone.getText().trim().isEmpty()  ){

                        System.out.println(" Preenchimento de todos os campos é obrigatorio!!!");

                }else{

                    String rua, bairro, cidade, estado, nome, dataNasc, email,cep;
                    int numCasa, telefone;
                    long cpff;
                    
                    rua = txtRua.getText();
                    bairro = txtBairro.getText();
                    cidade = txtCidade.getText();
                    estado = txtEstado.getText();
                    numCasa = Integer.parseInt(txtNumCasa.getText());
                    cep = txtCEP.getText();

                    Endereco endereco = new Endereco(rua,bairro,cidade,numCasa,cep,estado);
                    
                    nome = txtNome.getText();
                    cpff = Long.parseLong(txtCPF.getText());
                    dataNasc = txtDataNascimento.getText();
                    telefone = Integer.parseInt(txtTelefone.getText());
                    email = txtEmail.getText();

                    cliente = new PessoaFisica(nome,cpff,dataNasc,telefone,email,endereco);

                    if(ApiBD.salvarCliente(cliente)){
                        System.out.println("Cliente Salvo com sucesso!!");
                        Stage stage = (Stage) btnVoltar.getScene().getWindow();
                        Carrinho.finalizarCompra(cliente,stage);
                    }else{
                        System.out.println("Erro ao salvar Cliente!!");
                    }

                }
                
            }
        }

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
        if(event.getCode() == KeyCode.ENTER){
            acaoPesquisarCPF(null);
        }
    }

    @FXML
    void acaoSair(ActionEvent event) throws IOException {
        Stage stage = (Stage)btnSair.getScene().getWindow();
        App.telaLogin(stage);

    }

    @FXML
    void acaoSolicitarDesconto(ActionEvent event) {

        Carrinho.setDesconto("25.00");
        atualizarCarrinho();

    }

    @FXML
    void acaoVoltar(ActionEvent event) throws IOException {
        TelaVenda1Controller.setUsuarioLogado(usuarioLogado); 
        App.setRoot("telaVenda1");
    }

    @FXML
    void acaoPesquisarCPF(ActionEvent event) {
        
        String nome_cpf = txtCPFpesquisa.getText();
        nome_cpf = nome_cpf.trim();

        if(nome_cpf.isEmpty()){
            System.out.println("Campo de pesquisa está vazio!!");
        }else{
            System.out.println("Nome ou cpf pesquisa: " + nome_cpf);
            PessoaFisica cliente = ApiBD.pesquisarClietne(nome_cpf);
            
            if(cliente != null){
                
                txtCPFpesquisa.setText("");
                txtNome.setText(cliente.getNome());
                txtCPF.setText(Long.toString(cliente.getCPF()));
                txtDataNascimento.setText(cliente.getData_Nascimento());
                txtRua.setText(cliente.getRua());
                txtBairro.setText(cliente.getBairro());
                txtNumCasa.setText(Integer.toString(cliente.getNumCasa()));
                txtCEP.setText(cliente.getCep());
                txtCidade.setText(cliente.getCidade());
                txtEstado.setText(cliente.getEstado());
                txtTelefone.setText(Integer.toString(cliente.getTelefone()));
                txtEmail.setText(cliente.getEmail());
                
                bloquearEdicaoDados();

            }else{
                txtCPFpesquisa.setText("Nenhum resultado encontrado!");
            }
        }
        

    }

    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }

    private void atualizarCarrinho(){

        observableListCarrinho = FXCollections.observableArrayList(Carrinho.getListaItens());

        tbCarrinho.setItems(observableListCarrinho);
        
        lblSubTotal.setText(Carrinho.getSubTotalCarrinho());
        lblDesconto.setText(Carrinho.getValorDesconto());
        lblValorTotal.setText(Carrinho.getValorTotalCarrinho());

    }

    private void bloquearEdicaoDados(){
        
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

    }

    @FXML
    private void initialize() throws IOException {
        
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);

        tbColunaDescricaoCarrinho.setCellValueFactory(data -> data.getValue().descricaoProperty());

        tbColunaPrecoCarrinho.setCellValueFactory(data -> data.getValue().getPrecoTotalProperty().asObject());

        tbColunaQuantidadeCarrinho.setCellValueFactory(data -> data.getValue().getQtdDoItemProperty().asObject());

        Carrinho.setDesconto("0.00");
        bloquearEdicaoDados();
        atualizarCarrinho();
        
    }

}
