package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.model.Anuncio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class PublicacionesCOntroller {
    AnuncioController controllerAnuncio;
    ObservableList<AnuncioDto> listaAnuncios = FXCollections.observableArrayList();
    AnuncioDto seleccionar;
    @FXML
    private HBox HBoxTableAnuncios;

    @FXML
    private TableView<AnuncioDto> tableAnuncios;

    @FXML
    private TableColumn<AnuncioDto, String> ColumnCodigo;

    @FXML
    private TableColumn<AnuncioDto, String> ColumnDescripcion;

    @FXML
    private TableColumn<AnuncioDto, String> ColumnFechaInicio;

    @FXML
    private TableColumn<AnuncioDto, String> ColumnFechaFinal;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtGechaini;

    @FXML
    private TextField txtFechaFinal;

    @FXML
    private HBox HboxImagen;

    @FXML
    private ImageView ImageAnuncio;
    @FXML
    private Button btnExportar;

    @FXML
    private Button btnEliminar;


    @FXML
    public void initialize() {
        controllerAnuncio = new AnuncioController();
        init();
    }

    public void init() {
        obtenerDatos();
        initDataBinding();
        listenerSelection();
        tableAnuncios.getItems().clear();
        tableAnuncios.setItems(listaAnuncios);
    }

    private void listenerSelection() {
        tableAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            seleccionar = newSelection;
            ponerImagen(seleccionar);

        });
    }

    public void initDataBinding() {
        ColumnCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        ColumnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        ColumnFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaInicio()));
        ColumnFechaFinal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaFIn()));
    }

    public void obtenerDatos() {
        listaAnuncios.addAll(controllerAnuncio.obtenerAnuncioDto());
    }

    public void ponerImagen(AnuncioDto anuncioDto) {

        try {
         Image   imagen = new Image(new FileInputStream(anuncioDto.rutaImagen().toString()));
            ImageAnuncio.setImage(imagen);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtCodigo.setText(anuncioDto.codigo());
        txtDescripcion.setText(anuncioDto.descripcion());
        txtGechaini.setText(anuncioDto.fechaInicio());
        txtFechaFinal.setText(anuncioDto.fechaFIn());
    }


    public void exportarAction(javafx.event.ActionEvent actionEvent) {

    }

    public void eliminarAction(ActionEvent actionEvent) {
    }
}

