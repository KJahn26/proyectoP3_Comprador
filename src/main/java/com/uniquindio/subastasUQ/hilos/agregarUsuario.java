package com.uniquindio.subastasUQ.hilos;

import com.uniquindio.subastasUQ.controlle.UsuarioController;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;

public class agregarUsuario extends Thread{
    UsuarioDto usuarioDto;
    UsuarioController usuarioControllerService;
    public agregarUsuario (UsuarioDto usuarioDto)
    {
        this.usuarioDto=usuarioDto;
        usuarioControllerService = new UsuarioController();
    }
    @Override
    public void run ()
    {
        usuarioControllerService.agregarUsuario(usuarioDto);
    }
}
