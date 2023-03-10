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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Classe responsável por controlar a tela de alterar nota fiscal
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
 */
public class ControllerAlterarNotaFiscal {

    @FXML
    private Button btnAlterarProduto;

    /**
     * btnLimpar usado para limpar os campos
     */
    @FXML
    private Button btnLimpar;

    /**
     * btnProcurar usado para procurar uma nota fiscal
     */
    @FXML
    private Button btnProcurar;

    /**
     * btnRemover usado para remover um produto da nota fiscal
     */
    @FXML
    private Button btnRemover;

    /**
     * btnSalvar usado para salvar a nota fiscal
     */
    @FXML
    private Button btnSalvar;

    /**
     * btnAdicionarProduto usado para adicionar um produto na nota fiscal
     */
    @FXML
    private Button btnAdicionarProduto;

    /**
     * datePickerVenda usado para selecionar a data da venda
     */
    @FXML
    private DatePicker datePickerVenda;

    /**
     * btnVoltar usado para voltar para a tela anterior
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * rootPane usado para carregar a tela
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * salvarAltera usado para salvar a alteração da nota fiscal
     */
    @FXML
    private Pane salvarAltera;

    /**
     * textFieldCodigo usado para receber o código da nota fiscal
     */
    @FXML
    private TextField textFieldCodigo;

    /**
     * textFieldCodigoProduto usado para receber o código do produto
     */
    @FXML
    private TextField textFieldCodigoProduto;

    /**
     * textFieldQuantidade usado para receber a quantidade do produto
     */
    @FXML
    private TextField textFieldQuantidade;

    /**
     * tableProdutos usado para mostrar os produtos da nota fiscal
     */
    @FXML
    private TableView<Item> tableProdutos;

    /**
     * tableColumnCodigo usado para mostrar o código do produto
     */
    @FXML
    private TableColumn<Item, Integer> tableColumnCodigo;

    /**
     * tableColumnNome usado para mostrar o nome do produto
     */
    @FXML
    private TableColumn<Item, String> tableColumnNome;

    /**
     * tableColumnPreco usado para mostrar o preço do produto
     */
    @FXML
    private TableColumn<Item, Double> tableColumnPreco;

    /**
     * tableColumnQuantidade usado para mostrar a quantidade do produto
     */
    @FXML
    private TableColumn<Item, Integer> tableColumnQuantidade;

    /**
     * listaProdutos usado para receber a lista de notas fiscais
     */
    private ListaNotaFiscal listaNotaFiscal;

    /**
     * listaProdutos usado para receber a lista de produtos
     */
    private ListaProdutos listaProdutos;

    /**
     * listaItemAntes usado para receber a lista de itens antes da alteração
     */
    private ListaItem listaItemAntes;

    /**
     * initialize usado para inicializar a coluna codigo, nome, preço e quantidade e a lista de notas fiscais e produtos
     */
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

    /**
    * Método usado para alterar um produto da nota fiscal
    * @param event evento de clicar no botão
    */
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

    /**
    * Método usado para adicionar um produto na nota fiscal
    * @param event evento de clicar no botão
    */
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

    /**
    * Método para procurar uma venda usando o codigo como parâmetro
    * @param event Evento de clique no botão
    */
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

    /**
    * Método para remover um produto da nota fiscal
    * @param event Evento de clique no botão
    */
    @FXML
    void removerProduto(ActionEvent event) {
        try {
            int selectedIndex = tableProdutos.getSelectionModel().getSelectedIndex();
            tableProdutos.getItems().remove(selectedIndex);
        } catch (Exception e) {
            alertInterface("ERRO", "Selecione um item para remover", AlertType.ERROR);
        }
    }

    /**
    * Método para salvar as alterações feitas na nota fiscal
    * @param event Evento de clique no botão
    */
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

    @FXML
    void notHoverBtnAdicionar(MouseEvent event) {
        btnAdicionarProduto.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnAdicionar(MouseEvent event) {
        btnAdicionarProduto.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    @FXML
    void hoverBtnAlterar(MouseEvent event) {
        btnAlterarProduto.setStyle("-fx-background-color: #676508;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de procurar
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnProcurar(MouseEvent event) {
        btnProcurar.setStyle("-fx-background-color: #676508;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de remover
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #682121;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de salvar
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnSalvar(MouseEvent event) {
        btnSalvar.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de voltar
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de alterar
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnAlterar(MouseEvent event) {
        btnAlterarProduto.setStyle("-fx-background-color: #807d0a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de procurar
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnProcurar(MouseEvent event) {
        btnProcurar.setStyle("-fx-background-color: #807d0a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de remover
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #7d2727;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de salvar
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnSalvar(MouseEvent event) {
        btnSalvar.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
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

}