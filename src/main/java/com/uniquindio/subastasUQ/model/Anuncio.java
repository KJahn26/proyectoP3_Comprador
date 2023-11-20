package com.uniquindio.subastasUQ.model;


import java.io.Serializable;

public class Anuncio extends SubastaUq implements Serializable
{
   private static final long serialVersionUID = 1L;
   public Anuncio ()
   {}
   String codigo="";
   String rutaImagen="";
   String fechaInicio="";
   String fechaFIn="";
   String descripcion="";
   String cedulaAnunciante="";

   public String getRutaImagen() {
      return rutaImagen;
   }

   public void setRutaImagen(String rutaImagen) {
      this.rutaImagen = rutaImagen;
   }

   public String getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(String fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   public String getFechaFIn() {
      return fechaFIn;
   }

   public void setFechaFIn(String fechaFIn) {
      this.fechaFIn = fechaFIn;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getCedulaAnunciante() {
      return cedulaAnunciante;
   }

   public void setCedulaAnunciante(String cedulaAnunciante) {
      this.cedulaAnunciante = cedulaAnunciante;
   }

   public String getCodigo() {
      return codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }
}
