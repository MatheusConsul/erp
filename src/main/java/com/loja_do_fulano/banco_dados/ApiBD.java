package com.loja_do_fulano.banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import com.loja_do_fulano.setor_estoque.Produto;

import java.util.ArrayList;
import java.util.Collection;

public class ApiBD {

    private static Conexao_bd conexao = new Conexao_bd();
    private static Connection connection = null;
    //private static PreparedStatement pst =null;
    //private static ResultSet rs = null;

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
            
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,usuario);
            pst.setString(2,senha); 
            ResultSet rs = pst.executeQuery();
            
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

    
    public static List<Produto> buscaProdutos(String produtoPesquisado){
        
        List<Produto> retornoConsulta = new ArrayList<>(); 
        
        try {

            String sql = "SELECT * FROM produtos where descricao LIKE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,"%" + produtoPesquisado + "%");

            if(produtoPesquisado.matches("23[0-9]+")){
                int codigo = Integer.parseInt(produtoPesquisado);
                sql = "SELECT * FROM produtos where codigo=?";
                pst = connection.prepareStatement(sql);
                pst.setInt(1,codigo);
            }
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                
                int cod = 0;
                String desc = "vazio";
                int quant = 0;
                float prec = 0.00F;
                
                do {
                 
                    cod = rs.getInt("codigo");
                    desc = rs.getString("descricao");
                    quant = rs.getInt("quantidade");
                    prec = rs.getFloat("preco");

                    /*DecimalFormat formato = new DecimalFormat("#0.00");
                    String numeroFormatado = formato.format(prec);
                    prec = Float.parseFloat(numeroFormatado);*/

                    Produto prod = new Produto(cod,desc,quant,prec);
                    
                    retornoConsulta.add(prod);

                    System.out.println("Produto encontrado: " + rs.getString(2));

                }while(rs.next());

            }else{
                Produto prod = new Produto(0000,"Nunhum produto correspondente a pesquisa",00,00.0F);
                retornoConsulta.add(prod);
            }
            
           
        } catch (Exception e) {
            //retornoConsulta.add("ERRO AO BUSCAR USUARIO EM BD: ");
            //retornoConsulta.addAll((Collection<? extends String>) e);
            Produto prod = new Produto(0000,"Erro ao pesquisar no banco de dados",00,00.0F);
            retornoConsulta.add(prod);
        }    

        return retornoConsulta;
    }







    
}
