package com.example.controller.notaFiscal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.example.baseclasse.Item;
import com.example.baseclasse.NotaFiscal;
import com.example.baseclasse.Produto;
import com.example.baseclasse.ProdutoFracionado;
import com.example.baseclasse.ProdutoUnidade;
import com.example.controller.ControllerMenuPrincipal;
import com.example.listas.ListaItem;
import com.example.listas.ListaNotaFiscal;
import com.example.listas.ListaProdutos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerAddNotaFiscal {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnConcluir;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnAlterarProduto;

    @FXML
    private Button btnRemover;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TableView<Item> tableProdutos;

    @FXML
    private TableColumn<Item, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Item, String> tableColumnNome;

    @FXML
    private TableColumn<Item, Double> tableColumnPreco;

    @FXML
    private TableColumn<Item, Double> tableColumnQuantidade;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    private DatePicker datePickerVenda;

    private ListaProdutos listaProdutos;

    private ListaNotaFiscal listaNotaFiscal;

    private ListaItem listaItem;

    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Item, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Item, Double>("quantidade"));

        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
        listaNotaFiscal = ControllerMenuPrincipal.getListaNotaFiscal();

        listaItem = new ListaItem();
    }

    @FXML
    void adicionarProduto(ActionEvent event) {
        String codigo = textFieldCodigo.getText();
        String quantidade = textFieldQuantidade.getText();

        try {

            if (codigo.trim().isEmpty() || codigo == null) {
                throw new Exception("O campo código não pode estar vazio");
            }

            if (quantidade.trim().isEmpty() || quantidade == null) {
                throw new Exception("O campo quantidade não pode estar vazio");
            }

            double quantidadeDouble;
            int codigoInt;

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("O campo código deve ser um número");
            }

            try {
                quantidadeDouble = Double.parseDouble(quantidade);
            } catch (Exception e) {
                throw new Exception("O campo quantidade deve ser um número");
            }

            if(quantidadeDouble <= 0) {
                throw new Exception("O campo quantidade deve ser maior que zero");
            }

            if(codigoInt <= 0) {
                throw new Exception("O campo código deve ser maior que zero");
            }

            Produto produto = listaProdutos.getProduto(codigoInt);
            ObservableList<Item> observableList = tableProdutos.getItems();

            Item item;

            if (produto instanceof ProdutoUnidade) {

                int quantidadeInt = (int) quantidadeDouble;

                if (quantidadeInt != quantidadeDouble) {
                    throw new Exception("O campo quantidade deve ser um número inteiro");
                }

                ProdutoUnidade produtoUnidade = (ProdutoUnidade) produto;

                int quantidadeTotal = 0;

                for(int i = 0; i < observableList.size(); i++) {
                    Item itemLista = observableList.get(i);

                    if(itemLista.getCodigo() == produtoUnidade.getCodigo()) {
                        quantidadeTotal += itemLista.getQuantidade();
                        if(quantidadeTotal + quantidadeInt > produtoUnidade.getQuantidade()) {
                            throw new Exception("A quantidade de produtos em estoque é menor que a quantidade solicitada");
                        }
                        
                    }
                }


                item = new Item(produtoUnidade, quantidadeInt);
                listaItem.addItem(item);
            } else {
                ProdutoFracionado produtoFracionado = (ProdutoFracionado) produto;

                double quantidadeTotal = 0;

                for(int i = 0; i < observableList.size(); i++) {
                    Item itemLista = observableList.get(i);

                    if(itemLista.getCodigo() == produtoFracionado.getCodigo()) {
                        quantidadeTotal += itemLista.getQuantidade();
                        if(quantidadeTotal + quantidadeDouble > produtoFracionado.getQuantidade()) {
                            throw new Exception("A quantidade de produtos em estoque é menor que a quantidade solicitada");
                        }
                        
                    }
                }

                item = new Item(produtoFracionado, quantidadeDouble);
                listaItem.addItem(item);
            }

            limparCampos(null);
            observableList.add(item);
            tableProdutos.setItems(observableList);

        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }

    }

    @FXML
    void adicionarVenda(ActionEvent event) {
        ObservableList<Item> observableList = tableProdutos.getItems();

        try {

            if(datePickerVenda.getValue() == null) {
                throw new Exception("O campo data não pode estar vazio");
            }

            LocalDate localDate = datePickerVenda.getValue();

            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Calendar dataCalendar = Calendar.getInstance();
            dataCalendar.setTime(date);

            for(Item item : observableList) {
                double quantidadeSub = item.getQuantidade();
                int codigoProduto = item.getCodigo();


                listaProdutos.subQuantidade(codigoProduto, quantidadeSub);
            }

            listaNotaFiscal.addNotaFiscal(new NotaFiscal(dataCalendar, listaItem));

            textFieldCodigo.clear();
            textFieldQuantidade.clear();
            tableProdutos.getItems().clear();
            datePickerVenda.setValue(null);
            alertInterface("SUCESSO", "Venda adicionada com sucesso", AlertType.INFORMATION);
            
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }

    }

    @FXML
    void limparCampos(ActionEvent event) {
        textFieldCodigo.clear();
        textFieldQuantidade.clear();
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

    @FXML
    void alterarProduto(ActionEvent event) {

    }

    @FXML
    void removerProduto(ActionEvent event) {

    }

    @FXML
    void notHoverBtnAlterar(MouseEvent event) {
    }

    @FXML
    void hoverBtnAlterar(MouseEvent event) {
    }

    @FXML
    void notHoverBtnRemover(MouseEvent event) {
    }

    @FXML
    void hoverBtnRemover(MouseEvent event) {
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
    void notHoverBtnConcluir(MouseEvent event) {
        btnConcluir.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void hoverBtnAdicionar(MouseEvent event) {
        btnAdicionar.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnConcluir(MouseEvent event) {
        btnConcluir.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
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


}
