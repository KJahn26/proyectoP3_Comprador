package com.uniquindio.subastasUQ.controller.view;
import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.utils.Persistencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class controllerAnunciante {
    @FXML
    private Button Regresar;


    private void mostrarVentanaPrincipal (javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("hello-view.fxml"));
            AnchorPane rootLayout  = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);

            appStage.toFront();
            appStage.show();

        }catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    public void RegresarAction(javafx.event.ActionEvent event) {

        mostrarVentanaPrincipal(event);
        Persistencia.guardaRegistroLog("Anunciante",1,"cierre de sesion","Anunciante");

    }
}
