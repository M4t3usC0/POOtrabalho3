package com.example;

import java.util.ArrayList;

import com.example.baseclasse.*;
import com.example.listas.*;

public class App {
    public static void main(String[] args) {

        System.out.println("INICIO MAIN");

        Produto p = new Produto(1, "Produto 1", 10.0, 2);

        ListaProdutos lista = new ListaProdutos(new ArrayList<Produto>());

        System.out.println("ADD PRODUTO");
        try {
            lista.addProduto(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Teste Nulo");
        try {
            lista.addProduto(null);
        } catch (Exception e) {
            System.out.println("Deu nulo");
        }

    }
}