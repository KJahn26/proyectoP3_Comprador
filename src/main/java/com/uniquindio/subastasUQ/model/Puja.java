package com.uniquindio.subastasUQ.model;

import java.io.Serializable;

public class Puja  implements Serializable {
    private static final long serialVersionUID = 1L;
    String nombreComprador;

    String cedulaComprador;

    String nombreProducto;

    String nombreAnunciante;
    String cedulaAnunciante;

    String valorPuja;

    String fechaFinal;

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public String getValorPuja() {
        return valorPuja;
    }

    public void setValorPuja(String valorPuja) {
        this.valorPuja = valorPuja;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getCedulaComprador() {
        return cedulaComprador;
    }

    public void setCedulaComprador(String cedulaComprador) {
        this.cedulaComprador = cedulaComprador;
    }

    public String getCedulaAnunciante() {
        return cedulaAnunciante;
    }

    public void setCedulaAnunciante(String cedulaAnunciante) {
        this.cedulaAnunciante = cedulaAnunciante;
    }

}
