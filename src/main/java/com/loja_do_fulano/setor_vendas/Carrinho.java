package com.loja_do_fulano.setor_vendas;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.loja_do_fulano.main.App;
import com.loja_do_fulano.setor_estoque.Produto;
import com.loja_do_fulano.telas_controller.TelaVenda2Controller;

public class Carrinho {

    private static List<Item> carrinhoDeCompras = new ArrayList<>();
    //private static Float valorTotalCarrinho = 0.00F; 

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
        if(valorTotalCarrinho < 1000){
            DecimalFormat formato = new DecimalFormat("#0.00");
            valorTotal = formato.format(valorTotalCarrinho);
        }else{
            DecimalFormat formato = new DecimalFormat("#0,000.00");
            valorTotal = formato.format(valorTotalCarrinho);
        }
        
        return valorTotal;
    } 

    public static void continuarVenda() throws IOException{
        
        if(carrinhoDeCompras.size() == 0 || carrinhoDeCompras == null){
            System.out.println("Carrinho vazio, por favor selecione algum produto para continuar!");
        }else{
            App.setRoot("telaVenda2");
        }
        

    }
    


}
