package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.controlle.UsuarioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.Optional;

public class controllerAnuncio {


    AnuncioController anuncioControllerService;

    ObservableList<ProductoDto> listaProductos = FXCollections.observableArrayList();

    ProductoDto productoSeleccionado;
    UsuarioController usuarioControllerService;

    @FXML
    private Button btnCompra;

    @FXML
    private TableView<ProductoDto> tableProductos;

    @FXML
    private TableColumn<ProductoDto, String> tcDescripcionProducto ;

    @FXML
    private TableColumn<ProductoDto, String> tcTipoProducto;

    @FXML
    private TableColumn<ProductoDto, String> tcNombreProducto;

    @FXML
    private TableColumn<ProductoDto, String> tcNombreAnunciante;

    @FXML
    private Text tituloAnuncio;

    @FXML
    void initialize(){
        anuncioControllerService= new AnuncioController();
        initView();
    }

    public void initView(){
        initDataBinding();
        obtenerProducto();
        tableProductos.getItems().clear();
        tableProductos.setItems(listaProductos);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        tcDescripcionProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descProducto()));
        tcTipoProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoProducto()));
        tcNombreAnunciante.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().anunciante()));

        //tcNombreProducto.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
        //tcTipoProducto.setCellValueFactory(new PropertyValueFactory("tipoProducto"));
        //tcDescripcionProducto.setCellValueFactory(new PropertyValueFactory("descripcionProducto"));

    }

    private void obtenerProducto() {
        listaProductos.addAll(anuncioControllerService.obtenerProducto(false));

    }

    private void listenerSelection() {
        tableProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
        });
    }


    @FXML
    void comprarProducto(ActionEvent event) {
        tableProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
        });
        boolean productoVendido=false;
        if (productoSeleccionado!= null)
        {
            if (mostrarMensajeConfirmacion("¿Estas seguro de comprar el producto?"))

            {
                productoVendido=anuncioControllerService.eliminarProducto(productoSeleccionado.nombreProducto());
                if (productoVendido!=false)
                {
                    //REVISAR

                    String centinela=anuncioControllerService.obtenerFecha()+" Compro "+productoSeleccionado.nombreProducto()+" "+productoSeleccionado.anunciante();
                    Persistencia.guardarTransacciones(centinela);
                    Persistencia.guardaRegistroLog("Comprador",2,"compro un producto","Anuncio");

                    listaProductos.remove(productoSeleccionado);
                    productoSeleccionado=null;
                    tableProductos.getSelectionModel().clearSelection();
                    mostrarMensaje("Notificación usuario", "Producto Comprado", "Ha comprado el producto con éxito", Alert.AlertType.INFORMATION);


                }
                else
                {
                    mostrarMensaje("Notificación usuario", "Producto no comprado", "El Producto no se puede comprar", Alert.AlertType.ERROR);
                }

            }
            else
            {
                mostrarMensaje("Notificación usuario", "usuario no encontrado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private void mostrarMensaje(String msj, String header, String contenido, Alert.AlertType alertType){
        Alert aler= new Alert(alertType);
        aler.setTitle(msj);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }


    }

}
