package com.uniquindio.subastasUQ.hilos;

import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;

public class agregarCompra extends Thread{
    AnuncioController controllerAnuncio;
    ProductoDto productoDto;
    public agregarCompra (ProductoDto productoDto)
    {
        this.productoDto=productoDto;
        this.controllerAnuncio= new AnuncioController();
    }


    @Override
    public void run ()
    {
        controllerAnuncio.guardarProducto(productoDto);
    }
}
