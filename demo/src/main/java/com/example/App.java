package com.example;

import com.example.baseclasse.*;

public class App {
    public static void main(String[] args) {

        String nome = "Arroz";
        double preco = 10.00;
        double quantidade = 5.0;
        String descricao = "Arroz Branco";

        ProdutoUnidade produtoUnidade2 = new ProdutoUnidade("Feijão", 5.00, 5, "Feijão Preto");
        System.out.println(produtoUnidade2.getCodigo());

        try {
            ProdutoUnidade produtoUnidade = new ProdutoUnidade(nome, preco, quantidade, descricao);
            System.out.println(produtoUnidade.toString());
            System.out.println(produtoUnidade.getCodigo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ProdutoUnidade produtoUnidade3 = new ProdutoUnidade("Feijão", 5.00, 5, "Feijão Preto");
        System.out.println(produtoUnidade3.getCodigo());

    }
}