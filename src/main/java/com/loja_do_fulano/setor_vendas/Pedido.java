package com.loja_do_fulano.setor_vendas;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private int numPedido;
    private PessoaFisica cliente;
    private Carrinho carrinho;
    private String statusPedido = null;
    private static List<Item> carrinhoDeCompras = new ArrayList<>();
    private static Float desconto = 0.00F;

    public Pedido(PessoaFisica cliente, Carrinho carrinho){
        this.numPedido = 023022; // determinar automaticamente 
        this.cliente = cliente;
        this.carrinho = carrinho;
    }

    public static void finalizarCompra(PessoaFisica cliente){

        // Salvar todos os dados da compra 
        // Salvar no banco se for um cliente novo
        // verificar todos os dados inclusive se tem disponibilidade de estoque
        // Criar e salvar o pedido no banco de dados
        // serealizar a lista de produtos 

    }

    public void teste(){

        try {

            System.out.println("teste teste");
        
    } catch (Exception e) {
        


    }




    }
    




}
