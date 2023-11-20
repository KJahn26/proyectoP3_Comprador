package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

public class controllerProductosAdquiridos {
    ObservableList<ProductoDto> listaProductosAdquiridos = FXCollections.observableArrayList();
    AnuncioController anuncioController;
    ProductoDto seleccion;
    @FXML
    private TableView<ProductoDto> tableProductosAdquiridos;

    @FXML
    private TableColumn<ProductoDto, String> columnFOto;

    @FXML
    private TableColumn<ProductoDto, String> columnProducto;

    @FXML
    private TableColumn<ProductoDto, String> columnTipoProducto;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaAdquiridoi;

    @FXML
    private Button btnExportar;
    @FXML
    void initialize ()
    {
        anuncioController =  new AnuncioController();
        init();
    }
    public void init ()
    {
        cogerDatos();
        listenerSelectionPuja();
        initDataBindingProductos();
        tableProductosAdquiridos.getItems().clear();
        tableProductosAdquiridos.setItems(listaProductosAdquiridos);
    }

    @FXML
    void ActionExportar(ActionEvent event) {
        try {
            exportarArchivoscsv(event);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
    private void listenerSelectionPuja() {
        tableProductosAdquiridos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            seleccion= newSelection;

        });
    }
    public void exportarArchivoscsv(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV (*.csv)", "*.csv"));
        File archivo = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
            if (archivo != null) {
                exportarCSV(archivo);
            }


    }
    public void cogerDatos ()
    {
        listaProductosAdquiridos.addAll(anuncioController.obtenerProductoAdquirido());
    }

    private void exportarCSV(File archivo) {
        try (FileWriter writer = new FileWriter(archivo)) {
            // Aquí puedes escribir los datos que deseas en el archivo CSV

            writer.write(cogerDatosAExportar());
            System.out.println("Archivo CSV exportado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private String cogerDatosAExportar ()
    {
        String centinela="";
        for (int i=0;i<listaProductosAdquiridos.size();i+=1)
        {
            centinela+="Nombre producto: "+ listaProductosAdquiridos.get(i).nombreProducto()+
            " fecha adquirido: " + listaProductosAdquiridos.get(i).fechaAdquirido()+
            " tipo producto: " + listaProductosAdquiridos.get(i).tipoProducto()+
            " 12345"+"\n";
        }

        return centinela;
    }
    private void initDataBindingProductos() {
        columnProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnFechaAdquiridoi.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().fechaAdquirido()));
        columnTipoProducto.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().tipoProducto()));





    }

}
