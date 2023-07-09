package com.loja_do_fulano.banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.loja_do_fulano.setor_caixa.Pedido;
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

    public static Boolean salvarCliente(PessoaFisica cliente){
        Boolean salvo = false;

        try {
            
            String sql = "INSERT INTO clientes (CPF, nome, data_nascimento, telefone, email, rua, bairro, cidade, numero_casa, cep, estado)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,Long.toString(cliente.getCPF()));
            pst.setString(2,cliente.getNome());
            pst.setString(3,cliente.getData_Nascimento());
            pst.setString(4,Integer.toString(cliente.getTelefone()));
            pst.setString(5,cliente.getEmail());
            pst.setString(6,cliente.getRua());
            pst.setString(7,cliente.getBairro());
            pst.setString(8,cliente.getCidade());
            pst.setString(9,Integer.toString(cliente.getNumCasa()));
            pst.setString(10,cliente.getCep());
            pst.setString(11,cliente.getEstado()); 

            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                salvo = true;
                System.out.println("Salvo no BANCO DE DADOS com sucesso!");
            } else {
                System.out.println("Falha ao adicionar o cliente.");
                salvo = false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
           
        return salvo;
    }

    public static boolean salvarPedido(long cpf, String nome, String valorTotal, String subTotal, String valorDesconto, byte[] listaItens, String statusPedido,String tipoPagamento){
        
        boolean salvo = false;

        try {
            
            String sql = "INSERT INTO pedidos (cpf_cliente, nome_cliente, total, sub_total, desconto, lista_itens, status_pedido, tipo_pag) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,Long.toString(cpf));
            pst.setString(2,nome);
            pst.setString(3,valorTotal);
            pst.setString(4, subTotal);
            pst.setString(5, valorDesconto);
            pst.setBytes(6, listaItens);
            pst.setString(7, statusPedido);
            pst.setString(8, tipoPagamento);
 

            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                salvo = true;
                System.out.println("Pedido salvo no BANCO DE DADOS com sucesso!");
            } else {
                System.out.println("Falha ao salvar pedido de venda no banco!!.");
                salvo = false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salvo;
    }


    public static List<Pedido> pesquisarPedidosPorStatus(String statusPedido){
        
        List<Pedido> retornoConsulta = new ArrayList<>(); 
        
        try {

            String sql = "SELECT * FROM pedidos where status_pedido LIKE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,statusPedido);

            if(statusPedido.equals("todos") ){
                sql = "SELECT * FROM pedidos";
                pst = connection.prepareStatement(sql);

            }

            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){

                do {
                
                    Pedido pedido = new Pedido(
                    rs.getInt("num_pedido"),
                    rs.getString("cpf_cliente"),
                    rs.getString("nome_cliente"),
                    rs.getString("total"),
                    rs.getString("sub_total"),
                    rs.getString("desconto"),
                    rs.getString("status_pedido"),
                    rs.getString("tipo_pag"));
                    
                    retornoConsulta.add(pedido);

                }while(rs.next());

            }else{
                retornoConsulta = null;
            }
            
           
        } catch (Exception e) {
            //retornoConsulta.add("ERRO AO BUSCAR USUARIO EM BD: ");
            //retornoConsulta.addAll((Collection<? extends String>) e);
           retornoConsulta = null;
        }    

        return retornoConsulta;
    }

    public static boolean alterarStatusPedido(String novoStatus, int numPedido){
        boolean salvo = false;

        if(novoStatus.equals("Aprovado")||novoStatus.equals("Reprovado") || novoStatus.equals("Entregue")){

            try {
            
                String sql = "UPDATE pedidos SET status_pedido = ? WHERE num_pedido = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, novoStatus);
                pst.setInt(2, numPedido);
        
                int linhasAfetadas = pst.executeUpdate();
        
                if (linhasAfetadas > 0) {
                System.out.println("Atualização realizada com sucesso!");
                salvo = true;
                } else {
                    System.out.println("Nenhuma linha afetada pela atualização.");
                    salvo = false;
                }
            
            } catch (SQLException e) {
                System.out.println("Erro ao tentar altera status pedido:");
                e.printStackTrace();
                salvo = false;
                
            }

            return salvo;
        }else{
            salvo = false;
            System.out.println("Status de Pedido invalido!!!");
        }

        return salvo;
    }

    public static byte[] buscarItensPedido(int numPedido){
        
        byte[] itensSerealizados = null; 
        
        try {

            String sql = "SELECT * FROM pedidos where num_pedido LIKE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,numPedido);

            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                itensSerealizados = rs.getBytes("lista_itens");
            }else{
                itensSerealizados = null;
            }
            
           
        } catch (Exception e) {
            System.out.println("Erro ao buscar itens serealizados no banco de dados");
            e.printStackTrace();
           itensSerealizados = null;
        }    

        return itensSerealizados;
    }

    
}
