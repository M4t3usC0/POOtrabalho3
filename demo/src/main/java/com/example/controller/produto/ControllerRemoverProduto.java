package com.example.controller.produto;

import java.util.Optional;

import com.example.controller.ControllerMenuPrincipal;
import com.example.listas.ListaProdutos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerRemoverProduto {

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnRemover;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldCodigo;

    private ListaProdutos listaProdutos;

    @FXML
    void initialize() {
        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
    }

    @FXML
    void hoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #686868;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #682121;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void limparCampos(ActionEvent event) {
        textFieldCodigo.clear();
    }

    @FXML
    void notHoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #747474;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #7d2727;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void removerProduto(ActionEvent event) {
        String codigo = textFieldCodigo.getText();

        try {

            if (codigo.trim().isEmpty() || codigo == null) {
                throw new Exception("Preencha o campo de código!");
            }

            int codigoInt = 0;

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("O código deve ser um número inteiro!");
            }

            try {

                Optional<ButtonType> result = alertInterfaceConfirmacao();
                
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    listaProdutos.removeProduto(codigoInt);
                    limparCampos(null);
                    alertInterface("Sucesso!", "Produto com código " + codigoInt  + " removido com sucesso!", AlertType.INFORMATION);
                }

            } catch (Exception e) {
                throw e;
            }

        } catch (Exception e) {
            alertInterface("Erro!", e.getMessage(), AlertType.ERROR);
        }
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

    Optional<ButtonType> alertInterfaceConfirmacao() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Você deseja remover o produto?");
        return alert.showAndWait();
    }

}
