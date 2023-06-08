package com.loja_do_fulano.conexao_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_bd {
    
  private Connection con = null;

  private String hostName = null;
  private String userName = null;
  private String password = null;
  private String url = null;
  private String jdbcDriver = null;
  private String dataBaseName = null;
  private String dataBasePrefix = null;
  private String dabaBasePort = null;

  public Conexao_bd() {
    super();
    
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
      if (con == null) {
        Class.forName(jdbcDriver);
        con = DriverManager.getConnection(url, userName, password);
      } else if (con.isClosed()) {
        con = null;
        return getConexao();
      }
    } catch (ClassNotFoundException e) {

      //use um sistema de log apropriado.
  
      e.printStackTrace();
    } catch (SQLException e) {

      //use um sistema de log apropriado.
  
      e.printStackTrace();
    }

    return con;
  }

  public void closeConnection() {
    if (con != null) {
      try {
          con.close();
      } catch (SQLException e) {
          // use um sistema de log apropriado.
          e.printStackTrace();
      }
    }
  }
    




}



