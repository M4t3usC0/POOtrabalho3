package com.example.listas;

import java.util.ArrayList;
import com.example.baseclasse.Item;

public class ListaItem {
    
    private ArrayList<Item> listaItens;

    public ListaItem() {
        this.listaItens = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        if(item != null) {
            listaItens.add(item);
            return;
        }
        throw new IllegalArgumentException("Item não pode ser nulo.");
    }

    public void removeItem(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        listaItens.remove(item);
    }

    public Item getItem(int codigo) {
        for (Item item : listaItens) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item não encontrado.");
    }

    public double getTotal() {
        if(listaItens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula.");
        }
        if(listaItens.size() == 0) {
            throw new IllegalArgumentException("Lista de itens não pode ser vazia.");
        }

        double total = 0;
        for (Item item : listaItens) {
            total += item.getPreco();
        }
        return total;
    }

    public ArrayList<Item> getArray() {
        return listaItens;
    }

}