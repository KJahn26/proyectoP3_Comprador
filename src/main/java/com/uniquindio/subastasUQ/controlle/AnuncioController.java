package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.controlle.service.IAnuncioService;
import com.uniquindio.subastasUQ.exceptions.pujaException;
import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import com.uniquindio.subastasUQ.model.Anuncio;

import java.util.ArrayList;
import java.util.List;

public class AnuncioController implements IAnuncioService {

    ModelFactoryController modelFactoryController;

    public AnuncioController(){

        modelFactoryController = ModelFactoryController.getInstance();
        //consumirProductos();
    }


    public void consumirProductos(){
        modelFactoryController.consumirServicioProductos();
    }

    public List<ProductoDto> obtenerProducto(boolean flag) {
        return modelFactoryController.obtenerProductos(flag);
    }
    public List<ProductoDto> obtenerProductoAdquirido() {
        return modelFactoryController.obtenerProductosAdquiridos();
    }

    public boolean eliminarProducto(String s) {
        return modelFactoryController.eliminarProducto(s);
    }

    public List<PujaDto> obtenerProductosPuja(boolean flag){ return modelFactoryController.obtenerProductosPuja(flag);}

    public boolean guardarPuja(PujaDto pujaDto){
        return modelFactoryController.agregarPuja(pujaDto);
    }

    public boolean eliminarpuja(String nombre, String cedulaC,String cedulaA){ return modelFactoryController.eliminarPuja(nombre,cedulaC,cedulaA);}

    public String obtenerFecha(){
        return modelFactoryController.getFecha();
    }

    @Override
    public boolean guardarProducto(ProductoDto productoDto) {
        return modelFactoryController.agregarProducto(productoDto);
    }

    @Override
    public boolean guardarAnuncio(AnuncioDto anunciodto) {
        return modelFactoryController.agregarAnuncio(anunciodto);
    }


    public String getCedulaComprador(){
        return modelFactoryController.getCedulaComprador();
    }

    public String getNombreComprador(){
        return modelFactoryController.getNombreComprador();
    }

    public String getNombreProducto(){ return modelFactoryController.getNombreProducto();}

    public void setNombreProducto(String nombreProducto){ modelFactoryController.setNombreProducto(nombreProducto);}

    public String getCedulaAnunciante(){ return modelFactoryController.getCedulaAnunciante();}

    public String getNombreAnunciante(){
        return modelFactoryController.getNombreAnunciante();
    }

    public void setvalorInicial(String nombreP,String cedulaAnunciante,String valorPuja){
        modelFactoryController.setvalorpuja(nombreP,cedulaAnunciante,valorPuja);
    }
        public List<AnuncioDto> obtenerAnuncioDto ()
        {
            return modelFactoryController.obtenerAnuncios();
        }


    public void compradelProducto(String nombreP, String cedulaC, String cedulaA) throws pujaException {
        modelFactoryController.compraProducto(nombreP,cedulaC,cedulaA);
        //modelFactoryController.eliminarPuja(nombreP,cedulaC,cedulaA);
    }
}

