package com.example.baseclasse;

public class ProdutoUnidade extends Produto {
    
    public ProdutoUnidade(String nome, double preco, int quantidade, String descricao) {
        super(nome, preco, quantidade, descricao);
    } 

    public ProdutoUnidade(String nome, double preco, double quantidade, String descricao) {
        super(nome, preco, quantidade, descricao);

        try {
            int quantidadeInt = (int) quantidade;

            if (quantidadeInt != quantidade) {
                throw new Exception("Quantidade inválida");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void setQuantidade(int quantidade) { super.setQuantidade(quantidade); }

    @Override
    public String toString() { return super.toString(); }

}