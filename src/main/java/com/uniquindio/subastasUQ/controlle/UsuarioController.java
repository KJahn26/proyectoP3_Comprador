package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController {
    ModelFactoryController modelFactoryController;

    public UsuarioController(){

        modelFactoryController = ModelFactoryController.getInstance();
        //consumirServicioUsuario();
    }

    public void consumirServicioUsuario(){
        modelFactoryController.consumirServicioUsuarios();
    }

    public List<UsuarioDto> obtenerUsuario() {

        return modelFactoryController.obtenerUsuarios();
    }

    public boolean agregarUsuario(UsuarioDto usuarioDto) {

        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    public boolean eliminarEmpleado(String cedula) {
        return modelFactoryController.eliminarUusario(cedula);
    }


    public boolean actualizarEmpleado(String cedulaActual, UsuarioDto usuarioDto) {
        return modelFactoryController.actualizarUsuario(cedulaActual, usuarioDto);
    }
    public String fecha ()
    {

        return  modelFactoryController.cogerFecha();
    }

    public void extraerfecha(String fecha){
       modelFactoryController.setFecha(fecha);
    }

    public String getNombreComprador(){
        return modelFactoryController.getCedulaComprador();
    }

    public void setCedulaComprador(String n){
        modelFactoryController.setCedulaComprador(n);
    }

    public String getNombreAnunciante(){
        return modelFactoryController.getCedulaAnunciante();
    }

    public void setCedulaAnunciante(String n){
        modelFactoryController.setCedulaAnunciante(n);
    }

    public String getNombreProducto(){
        return modelFactoryController.getNombreProducto();
    }

    public void setNombreProducto(String n){
        modelFactoryController.setNombreProducto(n);
    }




}
