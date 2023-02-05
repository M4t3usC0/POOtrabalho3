package com.example.baseclasse;

public class Produto {

    private int codigo;
    private String nome;
    private double preco;
    private String descricao;
    private double quantidade;
    private static int codigoUnico = 10000;
    
    public Produto(String nome, double preco, double quantidade, String descricao) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }

        if(preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }

        if(quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if(descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        }

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

    public void diminuirCodigoUnico() { codigoUnico--; }
    
    @Override
    public String toString() {
        return "Codigo: " + codigo + ";" +
               "Nome: " + nome + ";" +
               "Preco: " + preco + ";" +
               "Quantidade: " + quantidade + ";" +
               "Descricao: " + descricao;
    }
    
}