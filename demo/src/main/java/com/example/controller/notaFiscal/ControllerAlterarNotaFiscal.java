package com.example.controller.notaFiscal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerAlterarNotaFiscal {

    @FXML
    private Button btnAlterarProduto;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnProcurar;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnSalvar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane salvarAltera;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldCodigo1;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    void alterarProduto(ActionEvent event) {

    }

    @FXML
    void hoverBtnAlterar(MouseEvent event) {

    }

    @FXML
    void hoverBtnLimpar(MouseEvent event) {

    }

    @FXML
    void hoverBtnProcurar(MouseEvent event) {

    }

    @FXML
    void hoverBtnRemover(MouseEvent event) {

    }

    @FXML
    void hoverBtnSalvar(MouseEvent event) {

    }

    @FXML
    void hoverBtnVoltar(MouseEvent event) {

    }

    @FXML
    void limparCampos(ActionEvent event) {

    }

    @FXML
    void notHoverBtnAlterar(MouseEvent event) {

    }

    @FXML
    void notHoverBtnLimpar(MouseEvent event) {

    }

    @FXML
    void notHoverBtnProcurar(MouseEvent event) {

    }

    @FXML
    void notHoverBtnRemover(MouseEvent event) {

    }

    @FXML
    void notHoverBtnSalvar(MouseEvent event) {

    }

    @FXML
    void notHoverBtnVoltar(MouseEvent event) {

    }

    @FXML
    void procurarVenda(MouseEvent event) {

    }

    @FXML
    void removerProduto(ActionEvent event) {

    }

    @FXML
    void salvarAlteracaoProduto(ActionEvent event) {

    }

    @FXML
    void voltarParaPrincipal(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/viewIndex.fxml"));
            Pane cmdPane = (Pane) fxmlLoader.load();

            rootPane.getChildren().clear();
            rootPane.getChildren().add(cmdPane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível voltar para o menu principal", AlertType.ERROR);
        }
    }

    void alertInterface(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
