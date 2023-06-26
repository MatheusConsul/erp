package com.loja_do_fulano.banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import com.loja_do_fulano.setor_estoque.Produto;
import com.loja_do_fulano.setor_vendas.Endereco;
import com.loja_do_fulano.setor_vendas.PessoaFisica;

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

            String sql = "SELECT * FROM estoque where descricao LIKE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,"%" + produtoPesquisado + "%");

            if(produtoPesquisado.matches("23[0-9]+")){
                int codigo = Integer.parseInt(produtoPesquisado);
                sql = "SELECT * FROM estoque where codigo=?";
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
                    prec = rs.getFloat("preco");
                    quant = rs.getInt("produto_disponivel_venda");

                    /*DecimalFormat formato = new DecimalFormat("#0.00");
                    String numeroFormatado = formato.format(prec);
                    prec = Float.parseFloat(numeroFormatado);*/

                    Produto prod = new Produto(cod,desc,quant,prec);
                    
                    retornoConsulta.add(prod);

                    //System.out.println("Produto encontrado: " + rs.getString(2));

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



    public static PessoaFisica pesquisarClietne(String nome_cpf){
        PessoaFisica cliente = null;
        
        System.out.println(" dentro da API de buscar cliente ");
        
        try {

            String sql = "SELECT * FROM clientes where nome LIKE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,"%" + nome_cpf + "%");

            if(nome_cpf.matches("\\d{11}") ||
                nome_cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
                sql = "SELECT * FROM clientes where CPF=?";
                pst = connection.prepareStatement(sql);
                pst.setString(1,nome_cpf);
            }
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                
                String rua, bairro, cidade, estado, nome, dataNasc, email,cep;
                int numCasa, telefone;
                long cpf;
                
                rua = rs.getString("rua");
                bairro = rs.getString("bairro");
                cidade = rs.getString("cidade");
                estado = rs.getString("estado");
                numCasa = rs.getInt("numero_casa");
                cep = rs.getString("cep");

                Endereco endereco = new Endereco(rua,bairro,cidade,numCasa,cep,estado);
                
                nome = rs.getString("nome");
                cpf = Long.parseLong(rs.getString("CPF"));
                dataNasc = rs.getString("data_nascimento");
                telefone = rs.getInt("telefone");
                email = rs.getString("email");

                cliente = new PessoaFisica(nome,cpf,dataNasc,telefone,email,endereco);

                //===============================================
                    System.out.println(" ++++++++++++ CLIENTE: ===========");

                    System.out.println("CPF: "+cliente.getCPF());
                    System.out.println("Nome: "+cliente.getNome());
                    System.out.println("DataN: "+cliente.getData_Nascimento());
                    System.out.println("telefone: "+cliente.getTelefone());
                    System.out.println("email: "+cliente.getEmail());
                    System.out.println("rua: "+cliente.getRua());
                    System.out.println("bairro: "+cliente.getBairro());
                    System.out.println("cidade: "+cliente.getCidade());
                    System.out.println("num casa: "+cliente.getNumCasa());
                    System.out.println("estado: "+cliente.getEstado());


                    System.out.println(" ++++++++++++++++++++++++++++++++");

                //===============================================
              

            }else{
                System.out.println("Nenhum cliente encontrado!!!");
            }
            
           
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados:");
            System.out.println(e);
        }    


        return cliente;
    }



    
}
