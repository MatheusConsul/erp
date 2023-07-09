package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.main.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaGerenteController {
    
    @FXML
    private Button btnAcompnharVenda;

    @FXML
    private Button btnEstoque;

    @FXML
    void abrirTelaEstoque(ActionEvent event) {

    }

    @FXML
    void acaoAcompanharVenda(ActionEvent event) {

    }

    @FXML
    private void abrirTelaEstoque() throws IOException {
        App.setRoot("telaEstoque");
    }
    
}
