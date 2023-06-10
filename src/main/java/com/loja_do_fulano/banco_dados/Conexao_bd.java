package com.loja_do_fulano.banco_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_bd {
    
  private Connection conexao = null;

  private  String hostName = null;
  private  String userName = null;
  private  String password = null;
  private  String url = null;
  private  String jdbcDriver = null;
  private  String dataBaseName = null;
  private  String dataBasePrefix = null;
  private  String dabaBasePort = null;

  public Conexao_bd() {
  
    hostName = "localhost";
    userName = "root";
    password = "";
    jdbcDriver = "com.mysql.jdbc.Driver";
    dataBaseName = "sistemaerp";
    dataBasePrefix = "jdbc:mysql://";
    dabaBasePort = "3306";
    
    url = dataBasePrefix + hostName + ":"+ dabaBasePort + "/" + dataBaseName;
      
    /**
      *Exemplo de um URL completo para MySQL:
      *a concatenação acima deve ficar algo como:
      *jdbc:'mysql:/localhost:3306/meu_bd'
      */
    
  }
      
  public Connection getConexao() {

    try {
      if (conexao == null) {
        Class.forName(jdbcDriver);
        conexao = DriverManager.getConnection(url, userName, password);
      } else if (conexao.isClosed()) {
        conexao = null;
        return getConexao();
      }
    } catch (ClassNotFoundException e) {
      //Fazer um log
      System.out.println("////////// ERRO DE CONEXÃO /////////");
      System.out.println(e);
      e.printStackTrace();
    } catch (SQLException e) {
      //fazer um log
      System.out.println("////////// ERRO DE CONEXÃO /////////");
      System.out.println(e);
      e.printStackTrace();
    }

    return conexao;
  }

  public void closeConnection() {
    if (conexao != null) {
      try {
        conexao.close();
      } catch (SQLException e) {
          // fazer um log
          System.out.println("////////// ERRO AO FECHAR A CONEXÃO /////////");
          System.out.println(e);
          e.printStackTrace();
      }
    }
  }
    




}



