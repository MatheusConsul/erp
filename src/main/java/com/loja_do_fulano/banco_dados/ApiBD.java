package com.loja_do_fulano.banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class ApiBD {

    private static Conexao_bd conexao = new Conexao_bd();
    private static Connection connection = null;
    private static PreparedStatement pst =null;
    private static ResultSet rs = null;

    public static Boolean conectar_bd(){
        boolean status = false;
        connection = conexao.getConexao();
        
        if(connection != null){
            status = true;
        }

        return status;
    }

    public static void fecharConexao(){
       conexao.closeConnection();
    }
    
    public static List<String> login(String usuario, String senha){
        
        List<String> retornoConsulta = new ArrayList<>(); 
        String sql = "SELECT * FROM usuarios where usuario=? and senha=?";
        
        try {
            
            pst = connection.prepareStatement(sql);
            pst.setString(1,usuario);
            pst.setString(2,senha); 
            rs = pst.executeQuery();
            
            if(rs.next()){

                // Adiciona o cargo da pessoa para a lista para retorna
                retornoConsulta.add(rs.getString(3));
                // Adiciona o nome do funcionario para a lista para retorna
                retornoConsulta.add(rs.getString(4));

            }else{
                retornoConsulta = null;
            }
            
           
        } catch (Exception e) {
            retornoConsulta.add("ERRO AO BUSCAR USUARIO EM BD: ");
            retornoConsulta.addAll((Collection<? extends String>) e);
            return retornoConsulta;
        }    

        return retornoConsulta;
    }






    
}
