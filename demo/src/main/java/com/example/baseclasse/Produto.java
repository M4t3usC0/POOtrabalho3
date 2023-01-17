package com.example.baseclasse;

public class Produto {
    
    private int codigo;
    private String nome;
    private double preco;
    private double quantidade;
    
    public Produto(int codigo, String nome, double preco, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    public int getCodigo() { return codigo; }

    public double getPreco() { return preco; }

    public String getNome() { return nome; }

    public double getQuantidade() { return quantidade; }
    
    public void setCodigo(int codigo) { this.codigo = codigo; }
    
    public void setNome(String nome) { this.nome = nome; }
    
    public void setPreco(double preco) { this.preco = preco; }
    
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }
    
    @Override
    public String toString() {
        return "Codigo: " + codigo + ";" +
        "Nome: " + nome + ";" +
        "Preco: " + preco + ";" +
        "Quantidade: " + quantidade;
    }
    
}