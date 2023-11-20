package com.uniquindio.subastasUQ.controller.view;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ProductosController {
    AnuncioController anuncioControllerService;
    ProductoDto productoSeleccionado;
    ObservableList<ProductoDto> listaProductos= FXCollections.observableArrayList();

    ArrayList<String> rutas = new ArrayList<>();

    @FXML
    private TableView<ProductoDto> tableUsuarios;

    @FXML
    private TableColumn<ProductoDto,String> columnNumeral;

    @FXML
    private TableColumn<ProductoDto, String> columnNombre;

    @FXML
    private TableColumn<ProductoDto, String> columnTipoProducto;

    @FXML
    private TableColumn<ProductoDto, String> columnDescripcion;

    @FXML
    private TableColumn<ProductoDto, String> columnNombreAnunciante;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaInicio;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaFinal;

    @FXML
    private TableColumn<ProductoDto, String> columnValorInicial;

    @FXML
    private Button btnPujar;
        @FXML
    private HBox hboxImageView;

    @FXML
    private ImageView ImageViewAnuncios;
    @FXML
    void initialize ()
    {

        anuncioControllerService = new AnuncioController();
        initDataBinding();
        obtenerDatos();
        cogerPosicion();
        datosDirectorio();
        tableUsuarios.getItems().clear();
        tableUsuarios.setItems(listaProductos);

        //anuncioControllerService.consumirProductos();
        //actualizarInterfaz();



    }

    public void actualizarInterfaz() {
        Platform.runLater(() -> {
            initDataBinding();
            obtenerDatos();
            cogerPosicion();
            datosDirectorio();
            tableUsuarios.getItems().clear();
            tableUsuarios.setItems(listaProductos);
        });
    }



    public void consumirProducto(){
        consume();
    }
    public void consume(){

        while(true){
            anuncioControllerService.consumirProductos();
            initDataBinding();
            obtenerDatos();
            cogerPosicion();
            datosDirectorio();
            tableUsuarios.getItems().clear();
            tableUsuarios.setItems(listaProductos);

        }

    }
    public void cogerPosicion ()
    {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int indiceSeleccionado = tableUsuarios.getSelectionModel().getSelectedIndex();
                ponerImagen(indiceSeleccionado);
            }
        });
    }
    public void ponerImagen (int posicion)
    {

        // Verificar si hay archivos en el directorio
        if (rutas != null && rutas.size() > 0 && posicion<=rutas.size()) {
            // Mostrar la primera imagen en el ImageView (puedes ajustar según tus necesidades)
            try {
                Image imagen = new Image(new FileInputStream(rutas.get(posicion)));
                ImageViewAnuncios.setImage(imagen);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El directorio está vacío o no contiene imágenes.");
        }

    }

    private void datosDirectorio ()
    {
         String rutaDirectorioImagenes = "src/main/resources/Imagenes"; // Ajusta la ruta según tu estructura de proyecto

        // Crear un objeto File que representa el directorio
        File directorio = new File(rutaDirectorioImagenes);

        // Obtener la lista de archivos en el directorio
         File [] archivos= directorio.listFiles();
         guaardarImagenes(archivos);
    }

    private void guaardarImagenes (File [] archivos)
    {
        for (int i=0;i<archivos.length;i+=1)
        {
            rutas.add(archivos[i].toString());
        }
    }

    private void listenerSelection() {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
        });
    }
    public void obtenerDatos ()
    {
        listaProductos.addAll(anuncioControllerService.obtenerProducto(false));
    }
    private void initDataBinding() {
        columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descProducto()));




    }

    @FXML
    void actionPujar(ActionEvent event) {
        mostrarMensaje("Notificación usuario", "usuario no a iniciado sesión o no esta registrado", "por favor ingrese sesión o registrese para poder hacer pujas", Alert.AlertType.ERROR);

    }
    private void mostrarMensaje(String msj, String header, String contenido, Alert.AlertType alertType){
        Alert aler= new Alert(alertType);
        aler.setTitle(msj);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }


}
