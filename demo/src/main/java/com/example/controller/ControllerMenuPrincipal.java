package com.example.controller;

import com.example.listas.ListaNotaFiscal;
import com.example.listas.ListaProdutos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerMenuPrincipal {

    @FXML
    private Button btnAdicionaNotaFiscal;

    @FXML
    private Button btnAdicionaProduto;

    @FXML
    private Button btnAlteraInformacoesNotaFiscal;

    @FXML
    private Button btnAlterarInformacoesProduto;

    @FXML
    private Button btnNotaFiscalExpandir;

    @FXML
    private Button btnProdutoExpandir;

    @FXML
    private Button btnRemoveNotaFiscal;

    @FXML
    private Button btnRemoveProduto;

    @FXML
    private Button btnVisualizaInformacoesNotaFiscal;

    @FXML
    private Button btnVisualizaInformacoesProduto;

    // @FXML
    // private Pane paneMenuClientesFechado;

    // @FXML
    // private Pane paneMenuLocacoesFechado;

    @FXML
    private Pane paneMenuNotaFiscalExpandido;

    @FXML
    private Pane paneMenuProdutoExpandido;

    @FXML
    private AnchorPane rootPane;

    private static ListaProdutos listaProdutos;

    private static ListaNotaFiscal listaNotaFiscal;

    @FXML
    void adicionaNotaFiscal(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/NotaFiscal/viewAddNotaFiscal.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de adicionar nova venda", AlertType.ERROR);
        }
    }

    @FXML
    void adicionaProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewAddProduto.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de adicionar produto", AlertType.ERROR);
        }
    }

    @FXML
    void alteraInformacoesNotaFiscal(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/NotaFiscal/viewAlterarNotaFiscal.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de remover produto", AlertType.ERROR);
        }
    }

    @FXML
    void alterarInformacoesProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewAlteraProduto.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            alertInterface("ERRO", "Não foi possível entrar na tela de alterar produto", AlertType.ERROR);
        }
    }

    @FXML
    void fecharMenuNotaFiscal(MouseEvent event) {
        paneMenuNotaFiscalExpandido.setVisible(false);
    }

    @FXML
    void fecharMenuProduto(MouseEvent event) {
        paneMenuProdutoExpandido.setVisible(false);
    }

    @FXML
    void hoverAdicionaNotaFiscal(MouseEvent event) {
        btnAdicionaNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    @FXML
    void hoverAdicionaProduto(MouseEvent event) {
        btnAdicionaProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    @FXML
    void hoverAlteraInformacoesNotaFiscal(MouseEvent event) {
        btnAlteraInformacoesNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    @FXML
    void hoverAlteraInformacoesProduto(MouseEvent event) {
        btnAlterarInformacoesProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    @FXML
    void hoverRemoveNotaFiscal(MouseEvent event) {
        btnRemoveNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    @FXML
    void hoverRemoveProduto(MouseEvent event) {
        btnRemoveProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    @FXML
    void hoverVisualizaInformacoesNotaFiscal(MouseEvent event) {
        btnVisualizaInformacoesNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    @FXML
    void hoverVisualizaInformacoesProduto(MouseEvent event) {
        btnVisualizaInformacoesProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    @FXML
    void mostrarMenuNotaFiscal(MouseEvent event) {
        paneMenuNotaFiscalExpandido.setVisible(true);
    }

    @FXML
    void mostrarMenuProduto(MouseEvent event) {
        paneMenuProdutoExpandido.setVisible(true);
    }

    @FXML
    void notHoverAdicionaNotaFiscal(MouseEvent event) {
        btnAdicionaNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    @FXML
    void notHoverAdicionaProduto(MouseEvent event) {
        btnAdicionaProduto.setStyle("-fx-background-color: #370617");
    }

    @FXML
    void notHoverAlteraInformacoesNotaFiscal(MouseEvent event) {
        btnAlteraInformacoesNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    @FXML
    void notHoverAlteraInformacoesProduto(MouseEvent event) {
        btnAlterarInformacoesProduto.setStyle("-fx-background-color: #370617");
    }

    @FXML
    void notHoverRemoveNotaFiscal(MouseEvent event) {
        btnRemoveNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    @FXML
    void notHoverRemoveProduto(MouseEvent event) {
        btnRemoveProduto.setStyle("-fx-background-color: #370617");
    }

    @FXML
    void notHoverVisualizaInformacoesNotaFiscal(MouseEvent event) {
        btnVisualizaInformacoesNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    @FXML
    void notHoverVisualizaInformacoesProduto(MouseEvent event) {
        btnVisualizaInformacoesProduto.setStyle("-fx-background-color: #370617");
    }

    @FXML
    void removeNotaFiscal(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/NotaFiscal/viewRemoverNotaFiscal.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de remover produto", AlertType.ERROR);
        }
    }

    @FXML
    void removeProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewRemoverProduto.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de remover produto", AlertType.ERROR);
        }
    }

    @FXML
    void visualizaInformacoesNotaFiscal(ActionEvent event) {

    }

    @FXML
    void visualizaInformacoesProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewVisualizaProdutos.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de visualizar informações do produto", AlertType.ERROR);
        }
    }

    void alertInterface(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void setListaProdutos(ListaProdutos listaProdutosNova) {
        listaProdutos = listaProdutosNova;
    }

    public void setListaNotaFiscal(ListaNotaFiscal ListaNotaFiscalNova) {
        listaNotaFiscal = ListaNotaFiscalNova;
    }

    public static ListaProdutos getListaProdutos() {
        return listaProdutos;
    }

    public static ListaNotaFiscal getListaNotaFiscal() {
        return listaNotaFiscal;
    }

}
