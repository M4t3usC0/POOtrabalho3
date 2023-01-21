package com.example.baseclasse;

import com.example.exceptions.produto.QuantidadeNotSupportedException;

public class Item {

    private Produto produto;
    private double quantidade;

    public Item(Produto produto, double quantidade) {

        if(produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if(quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if(produto instanceof ProdutoUnidade) {
            int quantidadeInt = (int) quantidade;

            if(quantidadeInt != quantidade) {
                throw new QuantidadeNotSupportedException("Quantidade não suportada.");
            }
        }

        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() { return produto; }

    public double getPreco() { return produto.getPreco(); }

    public double getQuantidade() { return quantidade; }

    public int getCodigo() { return produto.getCodigo(); }

    public void setProduto(Produto produto) { this.produto = produto; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return produto.toString() + ";" + 
               " Quantidade: " + quantidade + ";";
    }
}