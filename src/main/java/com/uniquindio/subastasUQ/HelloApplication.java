package com.uniquindio.subastasUQ;

import com.uniquindio.subastasUQ.exceptions.AbrirventanaException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    @Override
    public void start(Stage stage) throws AbrirventanaException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Subastas Universidad del Quindio");
        mostrarVentanaPrincipal();
    }


    public void mostrarVentanaPrincipal ()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("hello-view.fxml"));
            AnchorPane rootLayout  = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }



}