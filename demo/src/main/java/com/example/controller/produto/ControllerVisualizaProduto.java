package com.example.controller.produto;


import com.example.baseclasse.Produto;
import com.example.baseclasse.ProdutoFracionado;
import com.example.baseclasse.ProdutoUnidade;
import com.example.controller.ControllerMenuPrincipal;
import com.example.listas.ListaProdutos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerVisualizaProduto {

    @FXML
    private Button btnInformacaoProduto;

    @FXML
    private Button btnInformacaoTodosProdutos;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private RadioButton radioButtonFracionado;

    @FXML
    private RadioButton radioButtonUnidade;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableColumn<Produto, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Produto, String> tableColumnNome;

    @FXML
    private TableColumn<Produto, Double> tableColumnPreco;

    @FXML
    private TableColumn<Produto, Double> tableColumnQuantidade;

    @FXML
    private TableColumn<Produto, String> tableColumnDescricao;

    @FXML
    private TableView<Produto> tableViewInfoCompleta;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextArea textFieldDescricao;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldPreco;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    private Pane paneCamposProduto;

    private boolean mostrarEsconderCampos = true;

    private boolean mostrarEsconderTabela = true;

    private ListaProdutos listaProdutos;

    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Produto, Double>("quantidade"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));

        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
    }

    @FXML
    void informacaoTodosProdutos(ActionEvent event) {
        if(paneCamposProduto.isVisible()) {
            paneCamposProduto.setVisible(false);
            mostrarEsconderCampos = true;
        } 

        tableViewInfoCompleta.setVisible(mostrarEsconderTabela);
        mostrarEsconderTabela = !mostrarEsconderTabela;

        if(tableViewInfoCompleta.isVisible()) {

            

            ObservableList<Produto> observableList = FXCollections.observableArrayList();

            try {

                if(listaProdutos.isEmpty()) {
                    throw new Exception("Não há produtos cadastrados");
                }
        
                String[] dadosProduto = listaProdutos.toString().split("\n");

                for(String dados : dadosProduto) {
                    String[] campos = dados.split(";");

                    int codigo;

                    try {
                        codigo = Integer.parseInt(campos[0].split(": ")[1]);
                    } catch (Exception e) {
                        throw new Exception("Erro ao converter o código do produto para inteiro");
                    }

                    Produto produto = listaProdutos.getProduto(codigo);
                    observableList.add(produto);
                }

            } catch (Exception e) {
                alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
            }

            tableViewInfoCompleta.setItems(observableList);
        } 
    }

    @FXML
    void infoProduto(ActionEvent event) {
        String codigo = textFieldCodigo.getText();

        try {

            if(codigo.trim().isEmpty()) {
                throw new Exception("Preencha o campo Código");
            }

            int codigoInt;

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("Código deve ser um inteiro");
            }

            if(codigoInt < 0) {
                throw new Exception("Código deve ser um inteiro positivo");
            }

            Produto produto;

            try {
                produto = listaProdutos.getProduto(codigoInt);
            } catch (Exception e) {
                throw e;
            }

            String nomeProduto = produto.getNome();
            String descricaoProduto = produto.getDescricao();
            double precoProduto = produto.getPreco();

            textFieldNome.setText(nomeProduto);
            textFieldDescricao.setText(descricaoProduto);
            textFieldPreco.setText(precoProduto + "");
            
            if(produto instanceof ProdutoUnidade) {
                ProdutoUnidade produtoUnidade = (ProdutoUnidade) produto;
                int quantidadeProduto = (int) produtoUnidade.getQuantidade();

                radioButtonFracionado.setSelected(false);
                radioButtonUnidade.setSelected(true);
                textFieldQuantidade.setText(quantidadeProduto + "");

            } else if(produto instanceof ProdutoFracionado) {
                ProdutoFracionado produtoFracionado = (ProdutoFracionado) produto;
                double quantidadeProduto = produtoFracionado.getQuantidade();

                radioButtonFracionado.setSelected(true);
                radioButtonUnidade.setSelected(false);
                textFieldQuantidade.setText(quantidadeProduto + "");
            }

        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void limparCampos(ActionEvent event) {
        textFieldCodigo.clear();
        textFieldNome.clear();
        textFieldDescricao.clear();
        textFieldPreco.clear();
        textFieldQuantidade.clear();
        radioButtonUnidade.setSelected(false);
        radioButtonFracionado.setSelected(false);
    }

    @FXML
    void mostrarInformacaoProduto(ActionEvent event) {
        if(tableViewInfoCompleta.isVisible()) {
            tableViewInfoCompleta.setVisible(false);
            mostrarEsconderTabela = true;
        } 

        paneCamposProduto.setVisible(mostrarEsconderCampos);
        mostrarEsconderCampos = !mostrarEsconderCampos;
    }

    @FXML
    void voltarParaPrincipal(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/viewIndex.fxml"));
            Pane cmdPane = (Pane) fxmlLoader.load();

            rootPane.getChildren().clear();
            rootPane.getChildren().add(cmdPane);
        } catch (Exception e) {
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

    @FXML
    void notHoverBtnInformacaoProduto(MouseEvent event) {
        btnInformacaoProduto.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnInformacaoTodosProdutos(MouseEvent event) {
        btnInformacaoTodosProdutos.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #747474;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnPesquisar(MouseEvent event) {
        btnPesquisar.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void hoverBtnInformacaoProduto(MouseEvent event) {
        btnInformacaoProduto.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnInformacaoTodosProdutos(MouseEvent event) {
        btnInformacaoTodosProdutos.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #686868;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnPesquisar(MouseEvent event) {
        btnPesquisar.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}

