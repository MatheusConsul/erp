package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.main.App;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TelaLoginController{

    @FXML
    private TextField userField;
    @FXML
    private TextField senhaField;
    @FXML
    private Label lblSenhaIncorreta;
    @FXML
    private Label lblStatusConexao;

    private static Stage stageLogin;

    public static void setStageLogin(Stage stage) {
        stageLogin = stage;
    }
    
    @FXML
    private void acaoEntrar() throws IOException {

        String user = userField.getText();
        String senha = senhaField.getText();

        if(user.equals("gerente") && senha.equals("gerente")){
            App.setRoot("telaGerente");
            stageLogin.close();

        }else if (user.equals("estoque") && senha.equals("estoque")){
            App.setRoot("telaEstoque");
            stageLogin.close();

        }else if(user.equals("vendedor") && senha.equals("vendedor")){
            App.setRoot("telaVenda1");
            stageLogin.close();

        }else{
            senhaField.setText("");
        }
       
        
    }

    @FXML
    private void acaoCancelar() throws IOException {
        
        //Stage stage = (Stage) userField.getScene().getWindow();
        stageLogin.close();
        Platform.exit();
    }

    @FXML
    private void acaoEntrer(KeyEvent event) throws IOException{
        
        if (event.getCode() == KeyCode.ENTER) {
            acaoEntrar();
        } 
    }



    @FXML
    private void initialize() {
        stageLogin.setOnCloseRequest(event -> fecharSistema());
        
        if(App.statusConexao == true){
            lblStatusConexao.setText("Você esta conectado!");
            lblStatusConexao.setVisible(true);
        }else{
            lblStatusConexao.setText("Erro de conexão!");
            lblStatusConexao.setVisible(true);
        }
        
    }

    private void fecharSistema() {
        stageLogin.close();
        Platform.exit();
    }
    


}
