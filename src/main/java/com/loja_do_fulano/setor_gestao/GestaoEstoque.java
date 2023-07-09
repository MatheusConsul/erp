package com.loja_do_fulano.setor_gestao;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.loja_do_fulano.banco_dados.ApiBD;
import com.loja_do_fulano.setor_caixa.Pedido;
import com.loja_do_fulano.setor_vendas.Item;
import com.loja_do_fulano.setor_vendas.ItemSerializable;

public class GestaoEstoque {

    private static List<Pedido> todosPedidos = new ArrayList<>();

     public static List<Pedido> getListaPedidos(){

        todosPedidos = ApiBD.pesquisarPedidosPorStatus("todos");

        if(todosPedidos == null){
            System.out.println("Erro ao buscar pedidos Realizados!!!!!");
        }
        return todosPedidos;
    }

    public static List<Item> getListaItens(Pedido pedido){

     List<Item> itensDoPedido = new ArrayList<>();
     
     int numPedido = pedido.getNumPedido();

     byte[] itensSerealizados = ApiBD.buscarItensPedido(numPedido);
     
     try {

          ByteArrayInputStream bais = new ByteArrayInputStream(itensSerealizados);
          ObjectInputStream ois = new ObjectInputStream(bais);
        
          ArrayList<ItemSerializable> listaDesserializada =      (ArrayList<ItemSerializable>) ois.readObject();
        
          ois.close();
          bais.close();

          for (ItemSerializable itemSer : listaDesserializada) {

               /* System.out.println("============ ITENS DO PEDIDO: =========");
               System.out.println("CODIGO: "+itemSer.getCodigo()+" Desc: "+itemSer.getDescricao()+" QTD: "+itemSer.getQtdDoItem()); */
               
               Item item = new Item(itemSer.getCodigo(),itemSer.getDescricao(),itemSer.getQtdDoItem(),itemSer.getQtdDisponivelVenda(),itemSer.getPrecoUnitario());

               itensDoPedido.add(item);
           }

          
     } catch (Exception e) {
          System.out.println("Erro durante o processo de deserialização");
          
     }
     
     
     if(itensDoPedido == null){
            System.out.println("Erro ao buscar pedidos Realizados!!!!!");
     }

        return itensDoPedido;
    }

    
}
