package com.loja_do_fulano.telas_controller;

import java.io.IOException;

import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_vendas.Carrinho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TelaEstoqueController {

    private static String usuarioLogado = null;

    @FXML
    private Button btnEntregarMercadoria;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    void acaoEntregaMercadoria(ActionEvent event) throws IOException {

        TelaEntregaController.setUsuarioLogado(usuarioLogado);
        App.setRoot("telaEntrega");

    }



    public static void setUsuarioLogado(String usuario){
        usuarioLogado = usuario;
    }

    @FXML
    private void initialize() throws IOException {
        
        lblUsuarioLogado.setText("Bem vindo " + usuarioLogado);

    }
}