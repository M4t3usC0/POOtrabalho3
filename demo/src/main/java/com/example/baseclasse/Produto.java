package com.example.baseclasse;

public class Produto {
    
    private static int codigoUnico = 10000;
    private int codigo;
    private String nome;
    private double preco;
    private double quantidade;
    private String descricao;
    
    public Produto(String nome, double preco, double quantidade, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.codigo = codigoUnico++;
        this.quantidade = quantidade;
    }

    public int getCodigo() { return codigo; }

    public double getPreco() { return preco; }

    public String getNome() { return nome; }

    public double getQuantidade() { return quantidade; }

    public String getDescricao() { return descricao; }
    
    public void setCodigo(int codigo) { this.codigo = codigo; }
    
    public void setNome(String nome) { this.nome = nome; }
    
    public void setPreco(double preco) { this.preco = preco; }
    
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    @Override
    public String toString() {
        return "Codigo: " + codigo + ";" +
               "Nome: " + nome + ";" +
               "Preco: " + preco + ";" +
               "Quantidade: " + quantidade + ";" +
               "Descricao: " + descricao;
    }
    
}