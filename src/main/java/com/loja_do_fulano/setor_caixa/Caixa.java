package com.loja_do_fulano.setor_caixa;

import java.util.ArrayList;
import java.util.List;


import com.loja_do_fulano.banco_dados.ApiBD;


public class Caixa {
    
    private static List<Pedido> pedidosRealizados = new ArrayList<>();

    
    
    public static void reprovarCompra(Pedido pedido){
         boolean alterado = ApiBD.alterarStatusPedido("Reprovado", pedido.getNumPedido());
    }

    public static void aprovarCompra(Pedido pedido){
        boolean alterado = ApiBD.alterarStatusPedido("Aprovado", pedido.getNumPedido());
    }
    
    
    public static List<Pedido> getListaPedidos(){

        pedidosRealizados = ApiBD.pesquisarPedidosPorStatus("Realizado");

        if(pedidosRealizados == null){
            System.out.println("Erro ao buscar pedidos Realizados!!!!!");
        }
 
        return pedidosRealizados;
    }


}
