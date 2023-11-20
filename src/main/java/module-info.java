module com.uniquindio.proyectofinal2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mapstruct;
    requires java.logging;
    requires java.desktop;
    requires com.jfoenix;
    requires com.rabbitmq.client;


    //opens com.uniquindio.UQ to javafx.fxml;
    //exports com.uniquindio.UQ;
    //exports com.uniquindio.UQ.aplication;
    //opens com.uniquindio.UQ.aplication to javafx.fxml;




    exports com.uniquindio.subastasUQ.controller.view;
    opens com.uniquindio.subastasUQ.controller.view to javafx.fxml;
    //exports com.uniquindio.aplication;
    //opens com.uniquindio.aplication to javafx.fxml;
    exports com.uniquindio.subastasUQ;
    opens com.uniquindio.subastasUQ to javafx.fxml;




    exports com.uniquindio.subastasUQ.mapping.mappings;
    exports com.uniquindio.subastasUQ.mapping.dto;
    exports com.uniquindio.subastasUQ.model;
}