package com.uniquindio.subastasUQ.controller.view;
import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class controllerPublicaciones {
    AnuncioController anuncioController;
    ProductoDto productoSeleccionado;
    ObservableList<ProductoDto> listaProductos = FXCollections.observableArrayList();

    ObservableList<PujaDto> listaProductosPuja = FXCollections.observableArrayList();

    @FXML
    private HBox hboxtable;

    @FXML
    private TableView<ProductoDto> tablePublicaciones;

    @FXML
    private TableColumn<ProductoDto, String> columnId;

    @FXML
    private TableColumn<ProductoDto, String>columnNombre;

    @FXML
    private TableColumn<ProductoDto, String> columnTipoProducto;

    @FXML
    private TableColumn<ProductoDto, String> columnDescripcion;

    @FXML
    private TableColumn<ProductoDto, String> columnNombreAnunciante;

    @FXML
    private TableColumn<ProductoDto, String> ColumnFechaInicio;

    @FXML
    private TableColumn<ProductoDto, String> ColumnFechaFInal;

    @FXML
    private TableColumn<ProductoDto, String> COlumnValorProducto;

    @FXML
    private HBox hboxNombreProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private HBox hboxTipoProducto;

    @FXML
    private TextField txtTipoProducto;

    @FXML
    private HBox hboxNombreAnunciante;

    @FXML
    private TextField txtNombreAnunciante;

    @FXML
    private HBox hboxFechaInicio;

    @FXML
    private TextField txtFechaInicio;

    @FXML
    private HBox hboxFechFInal;

    @FXML
    private TextField txtFechaFinal;

    @FXML
    private HBox hboxValorProducto;

    @FXML
    private TextField txtValorProducto;

    @FXML
    private HBox hboxImagenProducto;

    @FXML
    private ImageView imageviewProducto;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private Button btnEliminar;

    @FXML
    private HBox hboxActuzalizar;

    @FXML
    private Button btnActualizar;
    @FXML
    private HBox hboxDescripcionProducto;

    @FXML
    private TextField txtDescripcion;
    @FXML
    void initialize ()
    {
        anuncioController = new AnuncioController();
        inicializar();
    }



    public void inicializar ()
    {
        initDataBinding();
        obtnerProductos();
        tablePublicaciones.getItems().clear();
        tablePublicaciones.setItems(listaProductos);
        listenerSelection();

    }


    @FXML
    void ActionActualizar(ActionEvent event) {

    }

    @FXML
    void ActionEliminar(ActionEvent event) {
            eliminarProducto();
    }

    @FXML
    void actionMostrarPujas(ActionEvent event)
    {
        anuncioController.setNombreProducto(productoSeleccionado.nombreProducto());
        mostrarVentana(event,"controlDePujas.fxml","Pujas Realizadas");
    }

    private void listenerSelection() {
        tablePublicaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            mostrarInformacionProducto(productoSeleccionado);
        });
    }
    private void mostrarInformacionProducto(ProductoDto productoSeleccionado1) {
        if(productoSeleccionado1!= null){
            txtNombreAnunciante.setText(productoSeleccionado1.anunciante());
            txtNombreProducto.setText(productoSeleccionado1.nombreProducto());
            txtTipoProducto.setText(productoSeleccionado1.tipoProducto());
            txtValorProducto.setText(productoSeleccionado1.valorInicial());
            txtFechaFinal.setText(productoSeleccionado1.fechaTerminarPublicacion());
            txtFechaInicio.setText(productoSeleccionado1.fechaPublicacion());
            txtDescripcion.setText(productoSeleccionado1.descProducto());

        }
    }
    public void obtnerProductos ()
    {
        listaProductos.addAll(anuncioController.obtenerProducto(true));
    }
    private void initDataBinding() {

        columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descProducto()));
        columnNombreAnunciante.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().anunciante()));
        COlumnValorProducto.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().valorInicial()));
        columnTipoProducto.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().tipoProducto()));
        ColumnFechaFInal.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().fechaTerminarPublicacion()));
        ColumnFechaInicio.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().fechaPublicacion()));
    }

    public void obtenerPorductosPuja(){
        listaProductosPuja.addAll(anuncioController.obtenerProductosPuja(true));
    }


    private void initDataBindingPuja(){
    
    }

    public void eliminarProducto ()
    {
        listenerSelection();
        if (productoSeleccionado!=null)
        {
            if (anuncioController.eliminarProducto(productoSeleccionado.nombreProducto()))
            {
                listaProductos.remove(productoSeleccionado);
                tablePublicaciones.getSelectionModel().clearSelection();

            }
        }

    }

    private void mostrarVentana (ActionEvent event, String ruta, String centinela)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(ruta));
            //loader.setLocation(HelloApplication.class.getResource(ruta));
            AnchorPane rootLayout  = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setTitle(centinela);
            appStage.toFront();
            appStage.show();

        }catch (Exception e)
        {
            e.printStackTrace();

        }
    }

}
