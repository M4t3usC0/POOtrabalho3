package com.example.controller.produto;


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

    // @FXML
    // private TableColumn<?, ?> tableColumnCarteiraMotoristaInfoCompleta;

    // @FXML
    // private TableColumn<?, ?> tableColumnCpfInfoCompleta;

    // @FXML
    // private TableColumn<?, ?> tableColumnEnderecoInfoCompleta;

    // @FXML
    // private TableColumn<?, ?> tableColumnNomeInfoCompleta;

    // @FXML
    // private TableColumn<?, ?> tableColumnTelefoneInfoCompleta;

    // @FXML
    // private TableView<?> tableViewInfoCompleta;

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
    void InformacaoTodosProdutos(ActionEvent event) {

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
        btnVoltar.setImage(new Image("../../images/pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void infoProduto(ActionEvent event) {

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
        btnVoltar.setImage(new Image("../../images/pngVoltarHover.png"));
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

