package com.example.listas;

import java.util.ArrayList;
import com.example.baseclasse.Item;

public class ListaItem {
    
    private ArrayList<Item> itens;

    public ListaItem(ArrayList<Item> itens) {
        if(itens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula.");
        }
    
        this.itens = itens;
    }

    public void addItem(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
    
        itens.add(item);
    }

    public void removeItem(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        itens.remove(item);
    }

    public Item getItem(int codigo) {
        for (Item item : itens) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item não encontrado.");
    }

    public double getTotal() {
        if(itens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula.");
        }
        if(itens.size() == 0) {
            throw new IllegalArgumentException("Lista de itens não pode ser vazia.");
        }

        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }

}