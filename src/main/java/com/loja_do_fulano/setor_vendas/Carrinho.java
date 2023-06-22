package com.loja_do_fulano.setor_vendas;

import java.util.ArrayList;
import java.util.List;

import com.loja_do_fulano.setor_estoque.Produto;

public class Carrinho {
    private static List<Produto> carrinhoDeCompras = new ArrayList<>(); 

    public static void addItem(Produto item){
       // verificar se ja tem o item 
        carrinhoDeCompras.add(item);

        System.out.println("\n >>>Produto: " + item.getDescricao() + "\n");
        
    }

    public static  List<Produto> getListaProdutos(){
        for (Produto produto : carrinhoDeCompras) {
            String descricao = produto.getDescricao();
            System.out.println(descricao);
        }
        return carrinhoDeCompras;
    }
    
}
