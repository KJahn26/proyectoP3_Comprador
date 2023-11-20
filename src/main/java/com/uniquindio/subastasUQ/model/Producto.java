package com.uniquindio.subastasUQ.model;

import java.io.Serializable;

public class Producto extends SubastaUq implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String nombreProducto ="";
    private String tipoProducto ="";
    private String descProducto ="";

    private String anunciante="";

    private String cedulaAnunciante="";

    private String valorInicial="";

    private String fechaPublicacion="";
    private String fechaTerminarPublicacion="";
    private String fechaAdquirido="";

    private String cedulaAdquisicion="";

    public Producto ()
    {}

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        this.anunciante = anunciante;
    }
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(String valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getFechaTerminarPublicacion() {
        return fechaTerminarPublicacion;
    }

    public String getFechaAdquirido() {
        return fechaAdquirido;
    }

    public void setFechaAdquirido(String fechaAdquirido) {
        this.fechaAdquirido = fechaAdquirido;
    }

    public void setFechaTerminarPublicacion(String fechaTerminarPublicacion) {
        this.fechaTerminarPublicacion = fechaTerminarPublicacion;
    }

    public String getCedulaAnunciante() {
        return cedulaAnunciante;
    }

    public void setCedulaAnunciante(String cedulaAnunciante) {
        this.cedulaAnunciante = cedulaAnunciante;
    }

    public String getCedulaAdquisicion() {
        return cedulaAdquisicion;
    }

    public void setCedulaAdquisicion(String cedulaAdquisicion) {
        this.cedulaAdquisicion = cedulaAdquisicion;
    }
}
