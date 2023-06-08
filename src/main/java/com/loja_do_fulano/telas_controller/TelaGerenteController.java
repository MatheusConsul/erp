package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.main.App;

import javafx.fxml.FXML;

public class TelaGerenteController {

    @FXML
    private void abrirTelaEstoque() throws IOException {
        App.setRoot("telaEstoque");
    }
}
