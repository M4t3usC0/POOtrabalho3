package com.example.controller.produto;

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

/**
 * Classe responsável por controlar a tela de adicionar produto
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
 */
public class ControllerAddProduto {

    /**
     * btnAdicionar usado para adicionar um produto
     */
    @FXML
    private Button btnAdicionar;

    /**
     * btnLimpar usado para limpar os campos
     */
    @FXML
    private Button btnLimpar;

    /**
     * btnVoltar usado para voltar para a tela de menu principal
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * radioButtonFracionado usado para selecionar se o produto é fracionado
     */
    @FXML
    private RadioButton radioButtonFracionado;

    /**
     * radioButtonUnidade usado para selecionar se o produto é por unidade
     */
    @FXML
    private RadioButton radioButtonUnidade;

    /**
     * rootPane usado para carregar a tela de adicionar produto
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * textFieldDescricao usado para receber a descrição do produto
     */
    @FXML
    private TextArea textFieldDescricao;

    /**
     * textFieldNome usado para receber o nome do produto
     */
    @FXML
    private TextField textFieldNome;

    /**
     * textFieldPreco usado para receber o preço do produto
     */
    @FXML
    private TextField textFieldPreco;

    /**
     * textFieldQuantidade usado para receber a quantidade do produto
     */
    @FXML
    private TextField textFieldQuantidade;

    /**
     * listaProdutos usado para receber a lista de produtos
     */
    private ListaProdutos listaProdutos;

    /**
     * Método usado para inicializar a lista de produtos
     *
     */
    @FXML
    void initialize() {
        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
    }

    /**
    * Método usado para adicionar um produto a partir do nome, descrição, preço, quantidade, se é fracionado ou por unidade
    * @param event evento de clicar no botão
    */
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
                ProdutoUnidade produto = new ProdutoUnidade(nomeProduto, precoProdutoDouble, quantidadeInt, descricaoProduto);
                listaProdutos.addProduto(produto);
                codigoProdutoAdicionado = produto.getCodigo();
            } else {
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

    /**
    * Método usado descartar a seleção do radioButtonFracionado
    */
    @FXML
    void radioButtonUnidadeClick(ActionEvent event) {
        if(radioButtonFracionado.isSelected()) {
            radioButtonFracionado.setSelected(false);
        }
    }

    /**
    * Método usado descartar a seleção do radioButtonUnidade
    */
    @FXML
    void radioButtonFracionadoClick(ActionEvent event) {
        if(radioButtonUnidade.isSelected()) {
            radioButtonUnidade.setSelected(false);
        }
    }

    /**
    * Método usado para limpar os campos de texto
    * @param event evento de clicar no botão
    */
    @FXML
    void limparCampos(ActionEvent event) {
        textFieldNome.clear();
        textFieldDescricao.clear();
        textFieldPreco.clear();
        textFieldQuantidade.clear();
        radioButtonFracionado.setSelected(false);
        radioButtonUnidade.setSelected(false);
    }

    /**
    * Método usado para voltar para a tela principal
    * @param event evento de clicar no botão
    */
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

    /**
    * Método usado para mostrar uma mensagem de alerta
    * @param titulo título da mensagem
    * @param mensagem mensagem a ser mostrada
    * @param tipo tipo de alerta
    */
    void alertInterface(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de adicionar
     * @param event evento de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnAdicionar(MouseEvent event) {
        btnAdicionar.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de limpar
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #747474;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de voltar
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de adicionar
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnAdicionar(MouseEvent event) {
        btnAdicionar.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de limpar
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void hoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #686868;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de voltar
     * @param event evento hover ao passar o mouse no botão de voltar
     */
    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}