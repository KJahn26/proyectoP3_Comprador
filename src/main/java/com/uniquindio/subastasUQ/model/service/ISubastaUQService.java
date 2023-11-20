package com.uniquindio.subastasUQ.model.service;

import com.uniquindio.subastasUQ.exceptions.UsuarioException;
import com.uniquindio.subastasUQ.model.Usuario;

import java.util.ArrayList;

public interface ISubastaUQService {

    public Usuario obtenerUsuario(String cedula);
    public Boolean eliminarUsuario(String cedula)throws UsuarioException;
    public ArrayList<Usuario> obtenerEmpleados();
    boolean actualizarUsuario(String cedulaActual, Usuario usurios) throws UsuarioException;
}
