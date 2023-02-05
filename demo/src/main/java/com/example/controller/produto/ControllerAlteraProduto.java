package com.example.controller.produto;

import com.example.baseclasse.Produto;
import com.example.baseclasse.ProdutoFracionado;
import com.example.baseclasse.ProdutoUnidade;
import com.example.controller.ControllerMenuPrincipal;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerAlteraProduto {

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnProcurar;

    @FXML
    private Button btnSalvar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private RadioButton radioButtonFracionado;

    @FXML
    private RadioButton radioButtonUnidade;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane salvarAltera;

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

    private ListaProdutos listaProdutos;

    @FXML
    void initialize() {
        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
    }

    @FXML
    void hoverBtnLimpar(MouseEvent event) {

    }

    @FXML
    void hoverBtnProcurar(MouseEvent event) {

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
    void notHoverBtnLimpar(MouseEvent event) {

    }

    @FXML
    void notHoverBtnProcurar(MouseEvent event) {

    }

    @FXML
    void notHoverBtnSalvar(MouseEvent event) {

    }

    @FXML
    void notHoverBtnVoltar(MouseEvent event) {

    }

    @FXML
    void radioButtonUnidadeClick(ActionEvent event) {
        radioButtonFracionado.setSelected(false);
    }

    @FXML
    void radioButtonFracionadoClick(ActionEvent event) {
        radioButtonUnidade.setSelected(false);
    }


    @FXML
    void procurarProduto(ActionEvent event) {
        String codigo = textFieldCodigo.getText();

        try {

            if (codigo.trim().isEmpty()) {
                throw new Exception("Campo código deve ser preenchido com um inteiro!");
            }

            int codigoInt;

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("Código deve ser um número inteiro");
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

            if (produto instanceof ProdutoUnidade) {
                int quantidadeProduto = (int) produto.getQuantidade();
                textFieldQuantidade.setText(quantidadeProduto + "");
                radioButtonUnidade.setSelected(true);
                radioButtonFracionado.setSelected(false);
            } else {
                double quantidadeProduto = produto.getQuantidade();
                textFieldQuantidade.setText(quantidadeProduto + "");
                radioButtonFracionado.setSelected(true);
                radioButtonUnidade.setSelected(false);
            }

        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void salvarAlteracaoProduto(ActionEvent event) {

        String nome = textFieldNome.getText();
        String descricao = textFieldDescricao.getText();
        String preco = textFieldPreco.getText();
        String quantidade = textFieldQuantidade.getText();
        String codigo = textFieldCodigo.getText();

        try {

            if (nome.trim().isEmpty()) {
                throw new Exception("Campo nome deve ser preenchido!");
            }

            if (descricao.trim().isEmpty()) {
                throw new Exception("Campo descrição deve ser preenchido!");
            }

            if (preco.trim().isEmpty()) {
                throw new Exception("Campo preço deve ser preenchido!");
            }

            if (quantidade.trim().isEmpty()) {
                throw new Exception("Campo quantidade deve ser preenchido!");
            }

            if (codigo.trim().isEmpty()) {
                throw new Exception("Campo código deve ser preenchido!");
            }

            double precoDouble;
            double quantidadeDouble;
            int codigoInt;

            try {
                precoDouble = Double.parseDouble(preco);
            } catch (Exception e) {
                throw new Exception("Preço deve ser um número real");
            }

            try {
                quantidadeDouble = Double.parseDouble(quantidade);
            } catch (Exception e) {
                throw new Exception("Quantidade deve ser um número real");
            }

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("Código deve ser um número inteiro");
            }

            if (quantidadeDouble < 0) {
                throw new Exception("Quantidade deve ser maior que zero");
            }

            if (precoDouble < 0) {
                throw new Exception("Preço deve ser maior que zero");
            }

            if (codigoInt < 0) {
                throw new Exception("Código deve ser maior que zero");
            }

            Produto produto = listaProdutos.getProduto(codigoInt);

            if (radioButtonUnidade.isSelected()) {

                int quantidadeInt = (int) quantidadeDouble;

                if (quantidadeInt != quantidadeDouble) {
                    throw new Exception("Quantidade deve ser um número inteiro");
                }

                if (produto instanceof ProdutoUnidade) {
                    ProdutoUnidade produtoUnidade = (ProdutoUnidade) produto;
                    produtoUnidade.setNome(nome);
                    produtoUnidade.setDescricao(descricao);
                    produtoUnidade.setPreco(precoDouble);
                    produtoUnidade.setQuantidade(quantidadeInt);
                } else {
                    ProdutoUnidade produtoUnidade = new ProdutoUnidade(nome, precoDouble, quantidadeInt, descricao);
                    produtoUnidade.setCodigo(produto.getCodigo());
                    listaProdutos.substituirProduto(produto, produtoUnidade);
                }

            } else {

                if (produto instanceof ProdutoFracionado) {
                    ProdutoFracionado produtoFracionado = (ProdutoFracionado) produto;
                    produtoFracionado.setNome(nome);
                    produtoFracionado.setDescricao(descricao);
                    produtoFracionado.setPreco(precoDouble);
                    produtoFracionado.setQuantidade(quantidadeDouble);
                } else {
                    ProdutoFracionado produtoFracionado = new ProdutoFracionado(nome, precoDouble, quantidadeDouble,
                            descricao);
                    produtoFracionado.setCodigo(produto.getCodigo());
                    listaProdutos.substituirProduto(produto, produtoFracionado);
                }
            }

        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
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

}
