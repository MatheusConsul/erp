package com.loja_do_fulano.telas_controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.loja_do_fulano.banco_dados.Conexao_bd;
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

    private Conexao_bd conexao = new Conexao_bd();
    private Connection con = null;
    private PreparedStatement pst =null;
    private ResultSet rs = null;


    private static Stage stageLogin;

    public static void setStageLogin(Stage stage) {
        stageLogin = stage;
    }
    
    @FXML
    private void acaoEntrar() throws IOException {

        String sql = "SELECT * FROM usuarios where usuario=? and senha=?";

        try {
            
            pst = con.prepareStatement(sql);
            pst.setString(1,txtUsuario.getText());
            //String senha = new String();
            pst.setString(2, txtSenha.getText()); 
            rs = pst.executeQuery();

            if(rs.next()){

                String cargo = rs.getString(3);
                if(cargo.equals("gerente")){
                    App.setRoot("telaGerente");
                }else if(cargo.equals("vendedor")){
                    TelaVenda1Controller.setUsuarioLogado(rs.getString(4));
                    App.setRoot("telaVenda1");
                }else if(cargo.equals("estoque")){
                    App.setRoot("telaEstoque");
                }else{
                    System.out.println("ERRO de comparação de cargo");
                }
                stageLogin.close();

            }else{
                txtSenha.setText("");
                lblSenhaIncorreta.setVisible(true);
            }

        } catch (Exception e) {
            System.out.println("ERRO AO BUSCAR USUARIO EM BD: " + e);
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
        
        con = conexao.getConexao();
        
        if(con == null){
            lblStatusConexao.setText("Erro de conexão!");
            lblStatusConexao.setVisible(true);
            btnEntrar.setVisible(false);
        }else{
            lblStatusConexao.setText("Você esta conectado!");
            lblStatusConexao.setVisible(true);
        }
        
    }

    private void fecharSistema() {
        stageLogin.close();
        Platform.exit();
    }
    


}
