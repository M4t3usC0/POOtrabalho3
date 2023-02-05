package com.example.baseclasse;

import com.example.exceptions.produto.QuantidadeNotSupportedException;

public class Item {

    private Produto produto;
    private int codigo;
    private String nome;
    private double preco;
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
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.codigo = produto.getCodigo();
        this.quantidade = quantidade;
    }

    public String getNome() { return nome; }

    public Produto getProduto() { return produto; }

    public double getPreco() { return preco; }

    public double getQuantidade() { return quantidade; }

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public void setNome(String nome) { this.nome = nome; }

    public void setPreco(double preco) { this.preco = preco; }

    public void setProduto(Produto produto) { this.produto = produto; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return produto.toString() + ";" + 
               " Quantidade: " + quantidade + ";";
    }
}