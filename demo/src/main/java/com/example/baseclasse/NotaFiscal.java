package com.example.baseclasse;

import java.util.Calendar;

import com.example.listas.ListaItem;

/**
 * A classe NotaFiscal modela uma nota fiscal do sistema.
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 * @version 1.0
 */
public class NotaFiscal {

    /**
     * O atributo codigo, do tipo int, identifica o código da nota fiscal.
     */
    private int codigo;
    /**
     * O atributo data, do tipo Calendar, identifica a data da nota fiscal.
     */
    private Calendar data;
    /**
     * O atributo itens, do tipo ListaItem, identifica a lista de itens da nota fiscal.
     */
    private ListaItem itens;
    /**
     * O atributo codigoUnico, do tipo int, identifica o código único da nota fiscal.
     */
    private static int codigoUnico = 10000;

    public NotaFiscal(Calendar data, ListaItem itens) {

        if(data == null) {
            throw new IllegalArgumentException("Data não pode ser nula.");
        }

        if(itens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula.");
        }
        
        this.data = data;
        this.itens = itens;
        this.codigo = codigoUnico++;
    }

    /**
     * O método add adiciona um item na lista de itens da nota fiscal.
     * @param item Item que será adicionado na lista de itens da nota fiscal.
     * @throws Exception
     */
    public void add(Item item) throws Exception { itens.addItem(item); }

    /**
     * O método remove remove um item da lista de itens da nota fiscal.
     * @param item Item que será removido da lista de itens da nota fiscal.
     * @throws Exception
     */
    public void remove(Item item) throws Exception { itens.removeItem(item); }

    /** 
     * @return int que identifica o codigo do item da nota fiscal.
     */
    public Item getItem(int codigo) throws Exception { return itens.getItem(codigo); }
    
    /**
     * @return double que identifica o total de itens na nota fiscal.
     */
    public double getTotal() throws Exception { return itens.getTotal(); }

    /**
     * @return int que identifica o codigo da nota fiscal.
     */
    public int getCodigo() { return codigo; }

    /**
     * @return Calendar que identifica a data da nota fiscal.
     */
    public Calendar getData() { return data; }

    /**
     * @return ListaItem que identifica a lista de itens da nota fiscal.
     */
    public ListaItem getItens() { return itens; }

    /**
    @param data Calendar que identifica a data da nota fiscal.
    */
    public void setData(Calendar data) { this.data = data; }

    /**
    @param itens ListaItem que identifica a lista de itens da nota fiscal.
    */
    public void setItens(ListaItem itens) { this.itens = itens; }

}