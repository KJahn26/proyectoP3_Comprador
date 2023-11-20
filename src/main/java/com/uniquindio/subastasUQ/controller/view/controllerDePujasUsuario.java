package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.exceptions.pujaException;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import com.uniquindio.subastasUQ.model.Producto;
import com.uniquindio.subastasUQ.model.Puja;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class controllerDePujasUsuario {

    ObservableList<PujaDto> listaPujas = FXCollections.observableArrayList();

    ObservableList<Producto> listaProductosAdquiridos = FXCollections.observableArrayList();
    AnuncioController anuncioController;

    PujaDto pujaseleccion;

    @FXML
    private HBox hboxPujas;

    @FXML
    private TableView<PujaDto> tablePujas;

    @FXML
    private TableColumn<PujaDto, String> columnComprador;

    @FXML
    private TableColumn<PujaDto, String> columnFechapuja;

    @FXML
    private TableColumn<PujaDto, String> columnNombreAnunciante;

    @FXML
    private TableColumn<PujaDto, String> columnNombreProducto;

    @FXML
    private TableColumn<PujaDto, String> columnValorPuja;

    @FXML
    private TextField txtFechaPuja;

    @FXML
    private TextField txtNOmbreUsuario;

    @FXML
    private TextField txtNombreAnunciante;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtValorPuja;

    @FXML
    void initialize(){
        anuncioController= new AnuncioController();
        inicializar();
    }

    void inicializar(){
        initDataBinding();
        obtenerPujas();
        tablePujas.getItems().clear();
        tablePujas.setItems(listaPujas);
        listenerSelectionPuja();
    }

    @FXML
    void RegresarAction(ActionEvent event) {
        mostrarVentana(event,"Anunciante.fxml","publicaciones");

    }
    @FXML
    void aceptarPujaAction(ActionEvent event) throws pujaException{
        aceptarPuja();
    }

    private void listenerSelectionPuja() {
        tablePujas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            pujaseleccion= newSelection;

        });
    }

    void initDataBinding(){
        columnComprador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreComprador()));
        columnNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnNombreAnunciante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreAnunciante()));
        columnFechapuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaFinal()));
        columnValorPuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valorPuja()));


    }

    void obtenerPujas(){

        listaPujas.addAll(anuncioController.obtenerProductosPuja(true));
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

    public void aceptarPuja() throws pujaException {
        if(pujaseleccion!=null){
            anuncioController.compradelProducto(pujaseleccion.nombreProducto(),pujaseleccion.cedulaComprador(),pujaseleccion.cedulaAnunciante());
        }
    }

}