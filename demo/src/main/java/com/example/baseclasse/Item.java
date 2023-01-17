package com.example.baseclasse;

public class Item {

    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade,double preco) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() { return produto; }

    public double getPreco() { return produto.getPreco(); }

    public int getQuantidade() { return quantidade; }

    public void setProduto(Produto produto) { this.produto = produto; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return  produto.toString() + ";" + 
               " Quantidade: " + quantidade + ";";
    }
}