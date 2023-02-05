package com.example.baseclasse;

public class ProdutoFracionado extends Produto {
    
    public ProdutoFracionado(String nome, double preco, double quantidade, String descricao) {
        super(nome, preco, quantidade, descricao);
    }
    
    public void setQuantidade(double quantidade) { super.setQuantidade(quantidade); }
    
    @Override
    public String toString() { return super.toString(); }
    
}
