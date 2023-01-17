import baseclasse.Produto;
import listas.ListaProdutos;

import java.util.ArrayList;

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