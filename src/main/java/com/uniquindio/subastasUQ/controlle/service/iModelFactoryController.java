package com.uniquindio.subastasUQ.controlle.service;

import com.uniquindio.subastasUQ.exceptions.UsuarioException;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;

import java.util.List;

public interface iModelFactoryController {

 List<UsuarioDto> obtenerUsuarios();

boolean agregarUsuario(UsuarioDto usuarioDto);

boolean eliminarUusario(String cedula);

boolean actualizarUsuario(String cedulaActual, UsuarioDto usuriosDto) throws UsuarioException;

boolean buscarEmpleado();
 public void cargarDatosArchivos ();



}
