package com.example.baseclasse;

import java.util.Calendar;

import com.example.listas.ListaItem;

public class NotaFiscal {

    private int codigo;
    private Calendar data;
    private ListaItem itens;
    private static int codigoUnico = 10000;

    public NotaFiscal(Calendar data, ListaItem itens) {
        
        this.data = data;
        this.itens = itens;
        this.codigo = codigoUnico++;
    }

    public void add(Item item) throws Exception { itens.addItem(item); }

    public void remove(Item item) throws Exception { itens.removeItem(item); }

    public Item getItem(int codigo) throws Exception { return itens.getItem(codigo); }
    
    public double getTotal() throws Exception { return itens.getTotal(); }

    public int getCodigo() { return codigo; }
    
    public Calendar getData() { return data; }

    public ListaItem getItens() { return itens; }

    public void setData(Calendar data) { this.data = data; }

    public void setItens(ListaItem itens) { this.itens = itens; }

}