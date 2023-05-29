package com.grupo2.telas_controller;

import java.io.IOException;
import com.grupo2.main.App;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaLoginController{

    @FXML
    private TextField userField;
    @FXML
    private TextField senhaField;
    @FXML
    private Label lblSenhaIncorreta;
    @FXML
    private VBox root;
    
    @FXML
    private void acaoEntrar() throws IOException {

        String user = userField.getText();
        String senha = senhaField.getText();

        if(user.equals("gerente") && senha.equals("gerente")){
            App.setRoot("telaGerente");

        }else if (user.equals("estoque") && senha.equals("estoque")){
            App.setRoot("telaEstoque");

        }else if(user.equals("vendedor") && senha.equals("vendedor")){
            App.setRoot("telaVenda1");

        }else{
            senhaField.setText("");
            lblSenhaIncorreta.setText("Usuario ou senha Incorreto!");
            lblSenhaIncorreta.setVisible(true);
        }
       
        
    }

    @FXML
    private void acaoCancelar() throws IOException {
        
        Stage stage = (Stage) userField.getScene().getWindow();
        stage.close();
        Platform.exit();

    }

    @FXML
    private void acaoEntrer(KeyEvent event) throws IOException{
        
        if (event.getCode() == KeyCode.ENTER) {
            acaoEntrar();
        } 

    }

    /*@FXML
    public void initialize() {
        sceneLogin.setOnCloseRequest(event -> {
            event.consume(); // Impede o fechamento padrão da janela
            Platform.exit(); // Fecha a aplicação por completo
        });
    }*/
    



}
