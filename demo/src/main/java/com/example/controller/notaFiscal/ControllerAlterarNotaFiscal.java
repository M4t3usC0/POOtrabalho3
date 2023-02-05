package com.example.controller.notafiscal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.example.baseclasse.Item;
import com.example.baseclasse.NotaFiscal;
import com.example.baseclasse.Produto;
import com.example.baseclasse.ProdutoUnidade;
import com.example.controller.ControllerMenuPrincipal;
import com.example.listas.ListaItem;
import com.example.listas.ListaNotaFiscal;
import com.example.listas.ListaProdutos;

import javafx.collections.FXCollections;
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
    private Button btnAdicionarProduto;

    @FXML
    private DatePicker datePickerVenda;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane salvarAltera;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldCodigoProduto;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    private TableView<Item> tableProdutos;

    @FXML
    private TableColumn<Item, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Item, String> tableColumnNome;

    @FXML
    private TableColumn<Item, Double> tableColumnPreco;

    @FXML
    private TableColumn<Item, Integer> tableColumnQuantidade;

    private ListaNotaFiscal listaNotaFiscal;

    private ListaProdutos listaProdutos;

    private ListaItem listaItemAntes;

    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Item, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantidade"));

        listaNotaFiscal = ControllerMenuPrincipal.getListaNotaFiscal();
        listaProdutos = ControllerMenuPrincipal.getListaProdutos();

        listaItemAntes = new ListaItem();
    }

    @FXML
    void alterarProduto(ActionEvent event) {
        try {
            int selectedIndex = tableProdutos.getSelectionModel().getSelectedIndex();
            Item item = tableProdutos.getItems().get(selectedIndex);

            textFieldCodigoProduto.setText(item.getCodigo() + "");

            if(item.getProduto() instanceof ProdutoUnidade) {
                textFieldQuantidade.setText((int) item.getQuantidade() + "");
            } else {
                textFieldQuantidade.setText(item.getQuantidade() + "");
            }

            tableProdutos.getItems().remove(selectedIndex);
        } catch (Exception e) {
            alertInterface("ERRO", "Selecione um item para alterar", AlertType.ERROR);
        }
    }

    @FXML
    void adicionarProduto(ActionEvent event) {
        String codigoNF = textFieldCodigo.getText();
        String codigoProduto = textFieldCodigoProduto.getText();

        try {

            if(codigoNF.trim().isEmpty() || codigoNF == null) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            int codigoNFInt;

            try {
                codigoNFInt = Integer.parseInt(codigoNF);
            } catch (Exception e) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            if(codigoProduto.trim().isEmpty() || codigoProduto == null) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            int codigoProdutoInt;

            try {
                codigoProdutoInt = Integer.parseInt(codigoProduto);
            } catch (Exception e) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            if(codigoProdutoInt <= 0) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            String quantidade = textFieldQuantidade.getText();

            if(quantidade.trim().isEmpty() || quantidade == null) {
                throw new Exception("Campo quantidade deve ser preenchido com um número inteiro");
            }

            double quantidadeDouble;

            try {
                quantidadeDouble = Double.parseDouble(quantidade);
            } catch (Exception e) {
                throw new Exception("Campo quantidade deve ser preenchido com um número inteiro");
            }

            if(quantidadeDouble <= 0) {
                throw new Exception("Campo quantidade deve ser preenchido com um número inteiro");
            }

            Produto produto = listaProdutos.getProduto(codigoProdutoInt);

            if(produto instanceof ProdutoUnidade) {

                int quantidadeInt = (int) quantidadeDouble;

                if(quantidadeDouble != quantidadeInt) {
                    throw new Exception("Campo quantidade deve ser preenchido com um número inteiro");
                }

            }

            NotaFiscal notaFiscal = listaNotaFiscal.getNotaFiscal(codigoNFInt);
            double quantidadeVendidaNFOriginal = 0;

            for(Item item : notaFiscal.getItens().getArray()) {
                if(item.getCodigo() == produto.getCodigo()) {
                    quantidadeVendidaNFOriginal = (int) item.getQuantidade();
                }
            }

            ObservableList<Item> listaProdutosNotaFiscal = tableProdutos.getItems();

            boolean flag = true;

            for(Item item : listaProdutosNotaFiscal) {                
                if(item.getProduto().getCodigo() == produto.getCodigo()) {
                    double quantidadeTotalEstoque = listaProdutos.getProduto(codigoProdutoInt).getQuantidade() + quantidadeVendidaNFOriginal;

                    double quantidadeAgoraTabela = item.getQuantidade();
                    double quantidadeQueroAdicionar = quantidadeDouble;

                    if(quantidadeTotalEstoque - (quantidadeAgoraTabela + quantidadeQueroAdicionar) < 0) {
                        throw new Exception("Quantidade de produtos no estoque é menor que a quantidade que deseja vender");
                    }

                    item.setQuantidade(item.getQuantidade() + quantidadeDouble);
                    tableProdutos.refresh();
                    flag = false;
                }
            }

            double quantidadeNotaFiscalAntes = 0;

            try {
                quantidadeNotaFiscalAntes = listaItemAntes.getItem(codigoProdutoInt).getQuantidade();
            } catch (Exception e) {}

            if(flag) {
                if(listaProdutos.getProduto(codigoProdutoInt).getQuantidade() < quantidadeDouble - quantidadeNotaFiscalAntes) {
                    throw new Exception("Quantidade de produtos no estoque é menor que a quantidade que deseja vender");
                }

                Item itemNotaFiscal = new Item(produto, quantidadeDouble);
                listaProdutosNotaFiscal.add(itemNotaFiscal);
                tableProdutos.refresh();
            }

            textFieldCodigoProduto.clear();
            textFieldQuantidade.clear();
       
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void procurarVenda(ActionEvent event) {
        String codigo = textFieldCodigo.getText();

        try {

            if(codigo.trim().isEmpty() || codigo == null) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            int codigoInt;

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            if(codigoInt <= 0) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            NotaFiscal notaFiscal;

            try {
                notaFiscal = listaNotaFiscal.getNotaFiscal(codigoInt);
            } catch (Exception e) {
                throw e;
            }

            textFieldCodigo.setText(notaFiscal.getCodigo() + "");

            Calendar data = notaFiscal.getData();
            Instant instant = data.toInstant();
            LocalDate dataLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            datePickerVenda.setValue(dataLocalDate);

            ObservableList<Item> listaProdutosNotaFiscal = FXCollections.observableArrayList();

            for(Item item : notaFiscal.getItens().getArray()) {
                Item itemNotaFiscal = new Item(item.getProduto(), item.getQuantidade());
                listaProdutosNotaFiscal.add(itemNotaFiscal);
            }

            tableProdutos.setItems(listaProdutosNotaFiscal);

            listaItemAntes = notaFiscal.getItens();

        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void removerProduto(ActionEvent event) {
        try {
            int selectedIndex = tableProdutos.getSelectionModel().getSelectedIndex();
            tableProdutos.getItems().remove(selectedIndex);
        } catch (Exception e) {
            alertInterface("ERRO", "Selecione um item para remover", AlertType.ERROR);
        }
    }

    @FXML
    void salvarAlteracaoProduto(ActionEvent event) {

        String codigoNF = textFieldCodigo.getText();
    
        try {

            if(codigoNF.trim().isEmpty() || codigoNF == null) {
                throw new Exception("Campo código não pode ser vazio");
            }

            int codigoNFInt;

            try {
                codigoNFInt = Integer.parseInt(codigoNF);
            } catch (Exception e) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            if(codigoNFInt <= 0) {
                throw new Exception("Campo código deve ser preenchido com um número positivo");
            }

            NotaFiscal notaFiscal;

            try {
                notaFiscal = listaNotaFiscal.getNotaFiscal(codigoNFInt);
            } catch (Exception e) {
                throw e;
            }

            for(Item item : listaItemAntes.getArray()) {
                listaProdutos.addQuantidade(item.getCodigo(), item.getQuantidade());
            }

            ListaItem listaItem = new ListaItem();

            for(int i = 0; i < tableProdutos.getItems().size(); i++) {
                Item item = tableProdutos.getItems().get(i);
               
                listaProdutos.subQuantidade(item.getCodigo(), item.getQuantidade());
                listaItem.addItem(item);
            }
            
            if(datePickerVenda.getValue() == null) {
                throw new Exception("Campo data deve ser preenchido");
            }

            LocalDate localDate = datePickerVenda.getValue();

            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Calendar dataCalendar = Calendar.getInstance();
            dataCalendar.setTime(date);

            notaFiscal.setData(dataCalendar);
            notaFiscal.setItens(listaItem);

            alertInterface("SUCESSO", "NOTA FISCAL ALTERADA COM SUCESSO!", AlertType.INFORMATION);
            textFieldCodigo.clear();
            textFieldCodigoProduto.clear();
            textFieldQuantidade.clear();
            datePickerVenda.setValue(null);
            tableProdutos.getItems().clear();
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

}
