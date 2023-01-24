package com.example.controller;

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

    @FXML
    private Pane paneMenuClientesFechado;

    @FXML
    private Pane paneMenuLocacoesFechado;

    @FXML
    private Pane paneMenuNotaFiscalExpandido;

    @FXML
    private Pane paneMenuProdutoExpandido;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void adicionaNotaFiscal(ActionEvent event) {

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

    }

    @FXML
    void alterarInformacoesProduto(ActionEvent event) {

    }

    @FXML
    void fecharMenuNotaFiscal(MouseEvent event) {

    }

    @FXML
    void fecharMenuProduto(MouseEvent event) {
        paneMenuProdutoExpandido.setVisible(false);

    }

    @FXML
    void hoverAdicionaNotaFiscal(MouseEvent event) {

    }

    @FXML
    void hoverAdicionaProduto(MouseEvent event) {

    }

    @FXML
    void hoverAlteraInformacoesNotaFiscal(MouseEvent event) {

    }

    @FXML
    void hoverAlteraInformacoesProduto(MouseEvent event) {

    }

    @FXML
    void hoverRemoveNotaFiscal(MouseEvent event) {

    }

    @FXML
    void hoverRemoveProduto(MouseEvent event) {

    }

    @FXML
    void hoverVisualizaInformacoesNotaFiscal(MouseEvent event) {

    }

    @FXML
    void hoverVisualizaInformacoesProduto(MouseEvent event) {

    }

    @FXML
    void mostrarMenuNotaFiscal(MouseEvent event) {
        
    }

    @FXML
    void mostrarMenuProduto(MouseEvent event) {
        paneMenuProdutoExpandido.setVisible(true);
    }

    @FXML
    void notHoverAdicionaNotaFiscal(MouseEvent event) {

    }

    @FXML
    void notHoverAdicionaProduto(MouseEvent event) {

    }

    @FXML
    void notHoverAlteraInformacoesNotaFiscal(MouseEvent event) {

    }

    @FXML
    void notHoverAlteraInformacoesProduto(MouseEvent event) {

    }

    @FXML
    void notHoverRemoveNotaFiscal(MouseEvent event) {

    }

    @FXML
    void notHoverRemoveProduto(MouseEvent event) {

    }

    @FXML
    void notHoverVisualizaInformacoesNotaFiscal(MouseEvent event) {

    }

    @FXML
    void notHoverVisualizaInformacoesProduto(MouseEvent event) {

    }

    @FXML
    void removeNotaFiscal(ActionEvent event) {

    }

    @FXML
    void removeProduto(ActionEvent event) {

    }

    @FXML
    void visualizaInformacoesNotaFiscal(ActionEvent event) {

    }

    @FXML
    void visualizaInformacoesProduto(ActionEvent event) {

    }

    void alertInterface(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
