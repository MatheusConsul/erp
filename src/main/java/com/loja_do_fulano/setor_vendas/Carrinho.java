package com.loja_do_fulano.setor_vendas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.loja_do_fulano.setor_estoque.Produto;

public class Carrinho {

    private static List<Produto> carrinhoDeCompras = new ArrayList<>();
    //private static Float valorTotalCarrinho = 0.00F; 

    public static void addItem(Produto item){ 

        Produto itemRecebido = new Produto(item.getCodigo(),item.getDescricao(), item.getQuantidade(), item.getPreco());
        boolean itemRepetido = false;
        
        itemRecebido.setQuantidade(1);

        if(carrinhoDeCompras.size() == 0){
            carrinhoDeCompras.add(itemRecebido);
        }else{
            for (Produto produto : carrinhoDeCompras) {
                
                if (produto.getCodigo() == itemRecebido.getCodigo()) {
                    produto.setQuantidade(produto.getQuantidade()+1);
                    produto.setPreco(itemRecebido.getPreco()*produto.getQuantidade());
                    itemRepetido = true;
                }
            }
            if(itemRepetido == false){
                carrinhoDeCompras.add(itemRecebido);
            }
        }
    }

    public static void excluirItem(Produto item){
        
        if(carrinhoDeCompras.size() == 0){
            System.out.println(" Carrinho está vazio");
        }else{
            Produto produto;
            for (int i = 0; i < carrinhoDeCompras.size(); i++) { 
                produto = carrinhoDeCompras.get(i);
                if (produto.getCodigo() == item.getCodigo()) {
                    carrinhoDeCompras.remove(i);
                }
            }
        }

    }

    public static void alterarQuantidade(Produto item, int novaQuant){

        if(carrinhoDeCompras.size() == 0){
            System.out.println(" Carrinho está vazio");
        }else{
            Produto produto;
            for (int i = 0; i < carrinhoDeCompras.size(); i++) { 
                produto = carrinhoDeCompras.get(i);
                if (produto.getCodigo() == item.getCodigo()) {
                    produto.setQuantidade(novaQuant);
                }
            }
        }
    }

    public static  List<Produto> getListaProdutos(){
         System.out.println("Lista do carrinho:");
        for (Produto produto : carrinhoDeCompras) {
            String descricao = produto.getDescricao();
            int quantidade = produto.getQuantidade();
            System.out.println(descricao + " - " + quantidade);
        }

        return carrinhoDeCompras;
    }

    public static String getValorTotalCarrinho(){
        Float valorTotalCarrinho= 0.00F;

        for (Produto produto : carrinhoDeCompras) {
              valorTotalCarrinho += produto.getPreco();
        }

        DecimalFormat formato = new DecimalFormat("#0,000.00");
        String valorTotal = formato.format(valorTotalCarrinho);
        return valorTotal;
    } 
    


}
