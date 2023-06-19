package com.loja_do_fulano.telas_controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.loja_do_fulano.banco_dados.ApiBD;
import com.loja_do_fulano.main.App;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TelaLoginController{

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblSenhaIncorreta;
    @FXML
    private Label lblStatusConexao;
    @FXML
    private Button btnEntrar;

    private static Stage stageLogin;

    public static void setStageLogin(Stage stage) {
        stageLogin = stage;
    }
    
    @FXML
    private void acaoEntrar() throws IOException {

        List<String> retornoConsulta = new ArrayList<>();
        retornoConsulta = ApiBD.login(txtUsuario.getText(),txtSenha.getText());
        
        if(retornoConsulta != null){

            if(retornoConsulta.get(0).equals("gerente")){
                App.setRoot("telaGerente");
                stageLogin.close();
            }else if(retornoConsulta.get(0).equals("vendedor")){
                TelaVenda1Controller.setUsuarioLogado(retornoConsulta.get(1));
                App.setRoot("telaVenda1");
                stageLogin.close();
            }else if(retornoConsulta.get(0).equals("estoque")){
                App.setRoot("telaEstoque");
                stageLogin.close();
            }else{
                for (String str : retornoConsulta) {
                    System.out.println(str);
                }
            }

        }else{
            txtSenha.setText("");
            lblSenhaIncorreta.setVisible(true);
        }

        
    }

    @FXML
    private void acaoCancelar() throws IOException {
        fecharSistema();
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

        if(ApiBD.conectar_bd() == false){

            lblStatusConexao.setText("Erro de conexão!");
            lblStatusConexao.setVisible(true);
            btnEntrar.setVisible(false);
        }else{
            lblStatusConexao.setText("Você esta conectado!");
            lblStatusConexao.setVisible(true);
        }
        
    }

    private void fecharSistema() {
        ApiBD.fecharConexao();
        stageLogin.close();
        Platform.exit();
    }
    
}
