package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.utils.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;

public class controllerComprador {



    public void RegresarAction(ActionEvent actionEvent) {

        mostrarVentanaPrincipal(actionEvent);
        Persistencia.guardaRegistroLog("Comprador",1,"cierre de sesion","Comprador");
    }

        public void mostrarVentanaPrincipal (ActionEvent actionEvent)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(HelloApplication.class.getResource("hello-view.fxml"));
                AnchorPane rootLayout  = (AnchorPane) loader.load();
                Scene scene = new Scene(rootLayout);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.toFront();
                appStage.show();

            }catch (Exception e)
            {
                e.printStackTrace();

            }

    }


}
