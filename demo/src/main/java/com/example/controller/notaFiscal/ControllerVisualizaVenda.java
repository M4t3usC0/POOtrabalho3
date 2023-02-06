package com.example.controller.notafiscal;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.baseclasse.Item;
import com.example.baseclasse.NotaFiscal;
import com.example.controller.ControllerMenuPrincipal;
import com.example.listas.ListaItem;
import com.example.listas.ListaNotaFiscal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerVisualizaVenda {

    @FXML
    private Button btnInformacaoNotaFiscal;

    @FXML
    private Button btnInformacaoTodosProdutos;

    @FXML
    private Button btnInformacaoTodosProdutos1;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private DatePicker datePickerVenda;

    @FXML
    private DatePicker datePickerVendaDia;

    @FXML
    private Pane paneInformacoesUmaNota;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableColumn<Item, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Item, String> tableColumnNome;

    @FXML
    private TableColumn<Item, Double> tableColumnPreco;

    @FXML
    private TableColumn<Item, Double> tableColumnQuantidade;

    @FXML
    private TableColumn<Item, String> tableColumnDescricao;

    @FXML
    private TableView<Item> tableProdutos;

    @FXML
    private TableView<NotaFiscal> tableTodasNotas;

    @FXML
    private TableColumn<NotaFiscal, Integer> tableColumnCodigoNotaFiscalTodas;

    @FXML
    private TableColumn<NotaFiscal, Calendar> tableColumnDataNotaFiscalTodas;

    @FXML
    private TableView<NotaFiscal> tableNotasDia;

    @FXML
    private TableColumn<NotaFiscal, Integer> tableColumnCodigoNotaFiscalDia;

    @FXML
    private TableColumn<NotaFiscal, Calendar> tableColumnDataNotaFiscalDia;

    @FXML
    private TextField textFieldTotalVendidoNotaDia;

    @FXML
    private TextField textFieldQuantidadeNotas;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldTotalVendidoNota;

    @FXML
    private Pane paneDiaEspecifico;

    @FXML
    private Pane paneTodasNotas;

    @FXML
    private Pane paneInformacoesTodasNotas;

    @FXML
    private Button btnProcurarNotaFiscalUnica;

    private ListaNotaFiscal listaNotaFiscal;

    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Item, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Item, Double>("quantidade"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));

        tableColumnCodigoNotaFiscalTodas.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Integer>("codigo"));

        tableColumnDataNotaFiscalTodas
                .setCellValueFactory(new Callback<CellDataFeatures<NotaFiscal, Calendar>, ObservableValue<Calendar>>() {
                    @Override
                    public ObservableValue<Calendar> call(CellDataFeatures<NotaFiscal, Calendar> dadosTabela) {
                        Calendar calendar = dadosTabela.getValue().getData();
                        return new SimpleObjectProperty<Calendar>(calendar);
                    }
                });
        tableColumnDataNotaFiscalTodas
                .setCellFactory(new Callback<TableColumn<NotaFiscal, Calendar>, TableCell<NotaFiscal, Calendar>>() {
                    @Override
                    public TableCell<NotaFiscal, Calendar> call(TableColumn<NotaFiscal, Calendar> column) {
                        return new TableCell<NotaFiscal, Calendar>() {
                            private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                            @Override
                            protected void updateItem(Calendar item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item == null || empty) {
                                    setText(null);
                                } else {
                                    setText(sdf.format(item.getTime()));
                                }
                            }
                        };
                    }
                });

        tableColumnCodigoNotaFiscalDia.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Integer>("codigo"));

        tableColumnDataNotaFiscalDia
                .setCellValueFactory(new Callback<CellDataFeatures<NotaFiscal, Calendar>, ObservableValue<Calendar>>() {
                    @Override
                    public ObservableValue<Calendar> call(CellDataFeatures<NotaFiscal, Calendar> dadosTabela) {
                        Calendar calendar = dadosTabela.getValue().getData();
                        return new SimpleObjectProperty<Calendar>(calendar);
                    }
                });

        tableColumnDataNotaFiscalDia
                .setCellFactory(new Callback<TableColumn<NotaFiscal, Calendar>, TableCell<NotaFiscal, Calendar>>() {
                    @Override
                    public TableCell<NotaFiscal, Calendar> call(TableColumn<NotaFiscal, Calendar> column) {
                        return new TableCell<NotaFiscal, Calendar>() {
                            private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                            @Override
                            protected void updateItem(Calendar item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item == null || empty) {
                                    setText(null);
                                } else {
                                    setText(sdf.format(item.getTime()));
                                }
                            }
                        };
                    }
                });


        listaNotaFiscal = ControllerMenuPrincipal.getListaNotaFiscal();
    }

    @FXML
    void informacaoTodosProdutos(ActionEvent event) {
        ObservableList<NotaFiscal> observableList = FXCollections.observableArrayList();

        for (NotaFiscal notaFiscal : listaNotaFiscal.getListaNotaFiscal()) {
            observableList.add(notaFiscal);
        }

        tableTodasNotas.setItems(observableList);
    }

    @FXML
    void informacaoUmaNota(ActionEvent event) {

        if(paneDiaEspecifico.isVisible() || paneTodasNotas.isVisible()) {
            paneDiaEspecifico.setVisible(false);
            paneTodasNotas.setVisible(false);
        }

        
    }

    @FXML
    void informacaoTodasNotas(ActionEvent event) {

    }

    @FXML
    void informacaoDiaEspecifico(ActionEvent event) {

    }

    @FXML
    void procurarNotaFiscalDia(ActionEvent event) {
        try {

            if(datePickerVendaDia.getValue() == null) {
                throw new Exception("O campo de data não pode estar vazio");
            }

            LocalDate localDate = datePickerVendaDia.getValue();

            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Calendar dataCalendar = Calendar.getInstance();
            dataCalendar.setTime(date);
           
            ArrayList<NotaFiscal> notasFicais = listaNotaFiscal.getNotasFiscaisPorData(dataCalendar);

            ObservableList<NotaFiscal> observableList = FXCollections.observableArrayList();

            double totalNotas = 0;

            for (NotaFiscal notaFiscal : notasFicais) {
                totalNotas += notaFiscal.getTotal();
                observableList.add(notaFiscal);
            }

            tableNotasDia.setItems(observableList);
            textFieldTotalVendidoNotaDia.setText(totalNotas + "");
            textFieldQuantidadeNotas.setText(notasFicais.size() + "");
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void procurarNotaFiscalUnica(ActionEvent event) {
        String codigoNF = textFieldCodigo.getText();

        try {
            if (codigoNF.trim().isEmpty() || codigoNF == null) {
                throw new Exception("O campo de código da nota fiscal não pode estar vazio");
            }

            int codigoNFInt;

            try {
                codigoNFInt = Integer.parseInt(codigoNF);
            } catch (Exception e) {
                throw new Exception("O campo de código da nota fiscal deve ser um número inteiro");
            }

            if (codigoNFInt <= 0) {
                throw new Exception("O campo de código da nota fiscal deve ser um número inteiro maior que zero");
            }

            NotaFiscal notaFiscal = listaNotaFiscal.getNotaFiscal(codigoNFInt);
            double totalNota = notaFiscal.getTotal();

            Calendar data = notaFiscal.getData();
            Instant instant = data.toInstant();
            LocalDate dataLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            ObservableList<Item> observableList = FXCollections.observableArrayList();

            ListaItem listaItem = notaFiscal.getItens();

            for (Item item : listaItem.getArray()) {
                observableList.add(item);
            }

            datePickerVenda.setValue(dataLocalDate);
            textFieldTotalVendidoNota.clear();
            textFieldTotalVendidoNota.setText(totalNota + "");
            tableProdutos.setItems(observableList);
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

    @FXML
    void notHoverBtnProcurarNotaFiscalUnica(ActionEvent event) {

    }

    @FXML
    void hoverBtnProcurarNotaFiscalUnica(ActionEvent event) {

    }

    @FXML
    void notHoverBtnInformacaoProduto(MouseEvent event) {

    }

    @FXML
    void notHoverBtnInformacaoTodosProdutos(MouseEvent event) {

    }

    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void hoverBtnInformacaoProduto(MouseEvent event) {

    }

    @FXML
    void hoverBtnInformacaoTodosProdutos(MouseEvent event) {

    }

    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com\\example\\images\\pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}
