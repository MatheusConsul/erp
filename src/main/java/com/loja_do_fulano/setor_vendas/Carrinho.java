package com.loja_do_fulano.setor_vendas;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import com.loja_do_fulano.banco_dados.ApiBD;

/*
 * 
    resumo: O Objetivo, metado usado, resultado*...
    Apresentação como vai ser realizado...
    Na intrudução relacionar as metrica e refatoração 
    

 */

import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_estoque.Produto;

import javafx.stage.Stage;

public class Carrinho {

    private static List<Item> carrinhoDeCompras = new ArrayList<>();
    private static Float desconto = 0.00F;

    public static void addItem(Produto produto){ 

        Item item_novo = new Item(produto.getCodigo(),produto.getDescricao(), 1,produto.getQtdDisponivelVenda(),produto.getPreco());
        boolean itemRepetido = false;

        if(carrinhoDeCompras.size() == 0){
            carrinhoDeCompras.add(item_novo);
        }else{
            for (Item item_carrinho : carrinhoDeCompras) {
                
                if (item_carrinho.getCodigo() == item_novo.getCodigo()) {
                    item_carrinho.setQtdDoItem(item_carrinho.getQtdDoItem()+1);
                    itemRepetido = true;
                }
            }
            if(itemRepetido == false){
                carrinhoDeCompras.add(item_novo);
            }
        }
    }

    public static void excluirItem(Item item){
        
        if(carrinhoDeCompras.size() == 0){
            System.out.println(" Carrinho está vazio");
        }else{
            Item itemDoCarrinho;
            for (int i = 0; i < carrinhoDeCompras.size(); i++) { 
                itemDoCarrinho = carrinhoDeCompras.get(i);
                if (itemDoCarrinho.getCodigo() == item.getCodigo()) {
                    carrinhoDeCompras.remove(i);
                }
            }
        }

    }

    public static void alterarQuantidade(Item item, int novaQuant){

        if(carrinhoDeCompras.size() == 0){
            System.out.println(" Carrinho está vazio");
        }else{
            Item itemDoCarrinho;
            for (int i = 0; i < carrinhoDeCompras.size(); i++) { 
                
                itemDoCarrinho = carrinhoDeCompras.get(i);

                if (itemDoCarrinho.getCodigo() == item.getCodigo()) {
                    itemDoCarrinho.setQtdDoItem(novaQuant);
                }
            }
        }
    }

    public static  List<Item> getListaItens(){
         
        //System.out.println("Lista do carrinho:");
        
         for (Item itemDoCarrinho : carrinhoDeCompras) {
            String descricao = itemDoCarrinho.getDescricao();
            int quantidade = itemDoCarrinho.getQtdDoItem();
            //System.out.println(descricao + " - " + quantidade);
        }
        return carrinhoDeCompras;
    }

    public static String getValorTotalCarrinho(){
        Float valorTotalCarrinho= 0.00F;
        String valorTotal = "0,00";

        for (Item itemDoCarrinho : carrinhoDeCompras) {
              valorTotalCarrinho += itemDoCarrinho.getPrecoTotal();
        }

        valorTotalCarrinho = valorTotalCarrinho - desconto;

        if(valorTotalCarrinho < 1000){
            DecimalFormat formato = new DecimalFormat("#0.00");
            valorTotal = formato.format(valorTotalCarrinho);
        }else{
            DecimalFormat formato = new DecimalFormat("#0,000.00");
            valorTotal = formato.format(valorTotalCarrinho);
        }
        
        return valorTotal;
    } 

    public static String getSubTotalCarrinho(){
        Float subTotalCarrinho= 0.00F;
        String subTotalString = "0,00";

        for (Item itemDoCarrinho : carrinhoDeCompras) {
              subTotalCarrinho += itemDoCarrinho.getPrecoTotal();
        }

        if(subTotalCarrinho < 1000){
            DecimalFormat formato = new DecimalFormat("#0.00");
            subTotalString = formato.format(subTotalCarrinho);
        }else{
            DecimalFormat formato = new DecimalFormat("#0,000.00");
            subTotalString = formato.format(subTotalCarrinho);
        }
        
        return subTotalString;
    } 

    public static String getValorDesconto(){
        String valorDesconto = "0,00";

        if(desconto < 1000){
            DecimalFormat formato = new DecimalFormat("#0.00");
            valorDesconto = formato.format(desconto);
        }else{
            DecimalFormat formato = new DecimalFormat("#0,000.00");
            valorDesconto = formato.format(desconto);
        }
        return valorDesconto;
    }

    public static void setDesconto(String desc){

        // Define o padrão da expressão regular para verificar um float positivo
        String padrao = "^\\d*\\.?\\d+$";

        if(Pattern.matches(padrao, desc)){
            desconto = Float.parseFloat(desc);
        }else{
            System.out.println("Valor de desconto invalido");
        }
        
        
    }

    public static void continuarVenda() throws IOException{
        
        if(carrinhoDeCompras.size() == 0 || carrinhoDeCompras == null){
            System.out.println("Carrinho vazio, por favor selecione algum produto para continuar!");
        }else{
            App.setRoot("telaVenda2");
        }
        
    }
    
    public static void finalizarCompra(PessoaFisica cliente, Stage stage) throws IOException{

        
        if(serealizarItens().equals(null)){
            System.out.println("Erro ao serealizar a lista de itens!!!");

        }else{
            byte[] listaSerealizada = serealizarItens();
            String tipoPagamento = "Dinheiro";
            String statusPedido = "Realizado";

            boolean pedidoSalvo = ApiBD.salvarPedido(cliente.getCPF(),cliente.getNome(), getValorTotalCarrinho(),getSubTotalCarrinho(), getValorDesconto(), listaSerealizada, statusPedido, tipoPagamento);

            if(pedidoSalvo){
                System.out.println("Pedido de venda salvo com sucesso!!!");
                carrinhoDeCompras.clear();
                App.telaLogin(stage);
            }else{
                System.out.println("Erro ao salvar pedido de venda!!!");
            }

        }

        
 
        // verificar todos os dados inclusive se tem disponibilidade de estoque
        // Criar e salvar o pedido no banco de dados


    }

    private static byte[] serealizarItens(){
        byte[] listaSerealizada = null;
        
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            List<ItemSerializable> listaItensSerializables = new ArrayList<>();

            for (Item itemDoCarrinho : carrinhoDeCompras) {

                ItemSerializable itemSerializable = new ItemSerializable(itemDoCarrinho.getCodigo(), itemDoCarrinho.getDescricao(), itemDoCarrinho.getQtdDoItem(),itemDoCarrinho.getQtdDisponivelVenda(), itemDoCarrinho.getPrecoUnitario());

                listaItensSerializables.add(itemSerializable);
            }
            
            oos.writeObject(listaItensSerializables);
            oos.close();
            listaSerealizada = baos.toByteArray();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listaSerealizada;
    }

}
