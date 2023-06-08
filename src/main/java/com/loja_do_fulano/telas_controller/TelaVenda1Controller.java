package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaVenda1Controller {

    @FXML
    private TextField txtfieldCampoPesquisa;
    @FXML
    private Label lblNomeVendedor;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TextArea txtAreaResulPesq;


    @FXML
    private void pesquisarProduto() throws IOException {
        
        String descricao = txtfieldCampoPesquisa.getText();
        txtAreaResulPesq.setText("Produto pesquisado foi: "+descricao);
        
    }
    
    
}
