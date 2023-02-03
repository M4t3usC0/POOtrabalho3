package com.example;

import com.example.baseclasse.Produto;
import com.example.baseclasse.ProdutoUnidade;
import com.example.controller.ControllerMenuPrincipal;

import com.example.listas.ListaProdutos;
import com.example.listas.ListaNotaFiscal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/viewIndex.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            ListaProdutos listaProdutos = new ListaProdutos();
            ListaNotaFiscal listaNotaFiscal = new ListaNotaFiscal();

            Produto p1 = new Produto("Arroz", 5.00, 10, "Produto perecivel");
            Produto p2 = new Produto("Feijão", 4.00, 20, "Produto perecivel");
            Produto p3 = new ProdutoUnidade("Coca-Cola", 5.00, 5, "Produto não perecivel");

            listaProdutos.addProduto(p1);
            listaProdutos.addProduto(p2);
            listaProdutos.addProduto(p3);

            ControllerMenuPrincipal controller = fxmlLoader.getController();
            controller.setListaProdutos(listaProdutos);
            controller.setListaNotaFiscal(listaNotaFiscal);

            

            // primaryStage.getIcons().add(new Image("images/pngIcon.png"));
            primaryStage.setTitle("Mercado Central");
            primaryStage.setScene(tela);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}