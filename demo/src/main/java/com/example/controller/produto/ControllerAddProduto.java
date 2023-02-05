package com.example.controller.produto;

import com.example.baseclasse.Produto;
import com.example.baseclasse.ProdutoFracionado;
import com.example.baseclasse.ProdutoUnidade;
import com.example.controller.ControllerMenuPrincipal;
import com.example.exceptions.geral.CampoVazioException;
import com.example.listas.ListaProdutos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerAddProduto {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnLimpar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private RadioButton radioButtonFracionado;

    @FXML
    private RadioButton radioButtonUnidade;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextArea textFieldDescricao;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldPreco;

    @FXML
    private TextField textFieldQuantidade;

    private ListaProdutos listaProdutos;

    @FXML
    void initialize() {
        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
    }

    @FXML
    void adicionarProduto(ActionEvent event) {

        String nomeProduto = textFieldNome.getText();
        String descricaoProduto = textFieldDescricao.getText();
        String precoProduto = textFieldPreco.getText();
        String quantidadeProduto = textFieldQuantidade.getText();
        boolean unidade = radioButtonUnidade.isSelected();
        boolean fracionado = radioButtonFracionado.isSelected();

        try {

            if(nomeProduto.trim().isEmpty() || nomeProduto == null) {
                throw new CampoVazioException("Nome do produto não pode ser vazio");
            }

            if(descricaoProduto.trim().isEmpty() || descricaoProduto == null) {
                throw new CampoVazioException("Descrição do produto não pode ser vazio");
            }

            if(precoProduto.trim().isEmpty() || precoProduto == null) {
                throw new CampoVazioException("Preço do produto não pode ser vazio");
            }

            if(quantidadeProduto.trim().isEmpty() || quantidadeProduto == null) {
                throw new CampoVazioException("Quantidade do produto não pode ser vazio");
            }

            if(!unidade && !fracionado) {
                throw new Exception("Selecione se o produto é fracionado ou por unidade");
            }

            double precoProdutoDouble = 0;
            double quantidadeProdutoDouble = 0;

            try {
                precoProdutoDouble = Double.parseDouble(precoProduto);
            } catch (Exception e) {
                throw new Exception("Preço do produto deve ser um número");
            }

            try {
                quantidadeProdutoDouble = Double.parseDouble(quantidadeProduto);
            } catch (Exception e) {
                throw new Exception("Quantidade do produto deve ser um número");
            }

            if(precoProdutoDouble <= 0) {
                throw new Exception("Preço do produto deve ser maior que 0");
            }

            if(quantidadeProdutoDouble <= 0) {
                throw new Exception("Quantidade do produto deve ser maior que 0");
            }

            int quantidadeInt = (int) quantidadeProdutoDouble;

            if(quantidadeInt != quantidadeProdutoDouble && unidade == true) {
                throw new Exception("Quantidade do produto deve ser um número inteiro");
            }

            int codigoProdutoAdicionado = 0;

            if(unidade == true) {
                // Adicionar produto por unidade
                ProdutoUnidade produto = new ProdutoUnidade(nomeProduto, precoProdutoDouble, quantidadeInt, descricaoProduto);
                listaProdutos.addProduto(produto);
                codigoProdutoAdicionado = produto.getCodigo();
            } else {
                // Adicionar produto fracionado
                ProdutoFracionado produto = new ProdutoFracionado(nomeProduto, precoProdutoDouble, quantidadeProdutoDouble, descricaoProduto);
                listaProdutos.addProduto(produto);
                codigoProdutoAdicionado = produto.getCodigo();
            }
            limparCampos(null);
            alertInterface("Sucesso", "Produto adicionado com sucesso\nCódigo do produto adicionado: " + codigoProdutoAdicionado, AlertType.INFORMATION);
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }

    }

    @FXML
    void radioButtonUnidadeClick(ActionEvent event) {
        if(radioButtonFracionado.isSelected()) {
            radioButtonFracionado.setSelected(false);
        }
    }

    @FXML
    void radioButtonFracionadoClick(ActionEvent event) {
        if(radioButtonUnidade.isSelected()) {
            radioButtonUnidade.setSelected(false);
        }
    }

    @FXML
    void hoverBtnAdicionar(MouseEvent event) {
        btnAdicionar.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #686868;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void limparCampos(ActionEvent event) {
        textFieldNome.clear();
        textFieldDescricao.clear();
        textFieldPreco.clear();
        textFieldQuantidade.clear();
        radioButtonFracionado.setSelected(false);
        radioButtonUnidade.setSelected(false);
    }

    @FXML
    void notHoverBtnAdicionar(MouseEvent event) {
        btnAdicionar.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #747474;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
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

