package com.grupo2.telas_controller;

import java.io.IOException;
import com.grupo2.main.App;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TelaLoginController{

    @FXML
    private TextField userField;
    @FXML
    private TextField senhaField;
    
    @FXML
    private void acaoEntrar() throws IOException {

        String user = userField.getText();
        String senha = senhaField.getText();

        //System.out.println("akiiiiiiii");

        if(user.equals("gerente") && senha.equals("gerente")){
            App.setRoot("telaGerente");

        }else if (user.equals("estoque") && senha.equals("estoque")){
            App.setRoot("telaEstoque");

        }else{
            senhaField.setText("");
            System.out.println("Erro de credenciais!");
        }
       
        
    }
    @FXML
    private void acaoCancelar() throws IOException {

        
        System.out.println("akiiiiiiii");

       
        
    }


}
