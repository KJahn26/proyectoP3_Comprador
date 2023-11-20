package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.UsuarioController;
import com.uniquindio.subastasUQ.hilos.agregarUsuario;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {
    UsuarioController usuarioControllerService;
    ObservableList<UsuarioDto> listaUsuarios = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;
    agregarUsuario AgregarUsuario;
    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtConfirmarContrasena;
    @FXML
    private Hyperlink TengoCuenta;
    @FXML
    private TextField txtNombre;


    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button NUevo;



    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtEmail;
   @FXML
    void initialize ()
   {
       usuarioControllerService =new UsuarioController();
       //consumirservio();
   }

   public void consumirservio(){
       while(true){
           usuarioControllerService.consumirServicioUsuario();
       }
   }

    public void RegistrarAction (ActionEvent event)
    {
        crearUsuario();
    }


    private void crearUsuario(){

        UsuarioDto usuarioDto= new UsuarioDto(txtNombre.getText(),txtTelefono.getText(),txtEmail.getText(),txtCedula.getText(),txtDireccion.getText(),txtContrasena.getText(),txtConfirmarContrasena.getText());
            AgregarUsuario = new agregarUsuario(usuarioDto);
        if(datosValidos(usuarioDto)){
            AgregarUsuario.run();
                listaUsuarios.add(usuarioDto);
                                limpiarCamposUsuario();

        }else{
            mostrarMensaje("Notificación usuario", "usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }
    private void limpiarCamposUsuario() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
    }




    private boolean datosValidos (UsuarioDto usuariosDto)
    {
        String mensaje = "";
        if(usuariosDto.nombre() == null || usuariosDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuariosDto.cedula() == null || usuariosDto.cedula().equals(""))
            mensaje += "El documento es invalido \n" ;
        if(usuariosDto.contrasena()== null || usuariosDto.contrasena().equals(""))
            mensaje += "La contraseña  es invalida \n" ;
        if (usuariosDto.telefono()==null || usuariosDto.telefono().equals(""))
            mensaje+="EL telefono invalido";
        if (usuariosDto.email()==null || usuariosDto.email().equals(""))
            mensaje+="El correo es invalido";
        if (usuariosDto.direccion()==null || usuariosDto.direccion().equals(""))
            mensaje="LA dirección invalida";

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    @FXML
    void NUevoAction(ActionEvent event) {
            txtNombre.setText(" Nombre");
            txtCedula.setText("Cedula");
            txtTelefono.setText("Telefono");
            txtDireccion.setText("Dirección");
            txtEmail.setText("E-mail");

    }
    @FXML
    void YatienesCuentaAction(ActionEvent event) {

        System.out.println("Ya tengo una cuenta");
    }

}
