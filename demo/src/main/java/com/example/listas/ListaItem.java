package com.example.listas;

import java.util.ArrayList;
import com.example.baseclasse.Item;

/**
* Classe responsável por controlar a lista de itens<br>
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
*/
public class ListaItem {
    
    /**
    * Lista de itens 
    */
    private ArrayList<Item> listaItens;

    /**
    * Construtor default da classe ListaItem<br>
    * Inicializa a lista de itens
    */
    public ListaItem() {
        this.listaItens = new ArrayList<Item>();
    }

    /**
    * Adiciona um item na lista de itens
    * @param item Item a ser adicionado
    */
    public void addItem(Item item) {
        if(item != null) {
            listaItens.add(item);
            return;
        }
        throw new IllegalArgumentException("Item não pode ser nulo.");
    }

    /**
    * Remove um item da lista de itens
    * @param item Item a ser removido
    */
    public void removeItem(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        listaItens.remove(item);
    }

    /**
    * Procura uma item na lista de itens
    * @param codigo Código do item a ser removido
    */
    public Item getItem(int codigo) {
        for (Item item : listaItens) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item não encontrado.");
    }

    /**
    * Retorna o total da lista de itens
    * @return Total da lista de itens
    */
    public double getTotal() {
        if(listaItens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula.");
        }
        if(listaItens.size() == 0) {
            throw new IllegalArgumentException("Lista de itens não pode ser vazia.");
        }

        double total = 0;
        for (Item item : listaItens) {
            total += item.getPrecoTotal();
        }
        return total;
    }

    /**
    * Retorna a lista de itens
    * @return Lista de itens
    */
    public ArrayList<Item> getArray() {
        return listaItens;
    }

    /**
    * Retorna o tamanho da lista de itens
    * @return Tamanho da lista de itens
    */
    public int size() {
        return listaItens.size();
    }

}