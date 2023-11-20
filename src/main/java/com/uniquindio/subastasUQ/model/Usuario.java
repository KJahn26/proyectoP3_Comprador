package com.uniquindio.subastasUQ.model;

import java.io.Serializable;

public class Usuario extends SubastaUq implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String nombre;

    private String confirmacioncontrasena;

    public String getConfirmacioncontrasena() {
        return confirmacioncontrasena;
    }

    public void setConfirmacioncontrasena(String confirmacioncontrasena) {
        this.confirmacioncontrasena = confirmacioncontrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    private  String email;

    private String cedula;

    private String contrasena;

    private String telefono;

    private String direccion;

    public Usuario(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }



    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
