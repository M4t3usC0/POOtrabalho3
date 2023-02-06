package com.example;

import com.example.controller.ControllerMenuPrincipal;

import com.example.listas.ListaProdutos;
import com.example.listas.ListaNotaFiscal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A classe App é responsável por iniciar a aplicação e carregar a tela inicial.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Método responsável por iniciar a aplicação e carregar a tela inicial.
     * @param primaryStage paramentro que recebe a tela inicial.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/viewIndex.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            ListaProdutos listaProdutos = new ListaProdutos();
            ListaNotaFiscal listaNotaFiscal = new ListaNotaFiscal();

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