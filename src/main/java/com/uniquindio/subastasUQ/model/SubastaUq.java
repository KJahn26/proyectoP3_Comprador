package com.uniquindio.subastasUQ.model;

import com.uniquindio.subastasUQ.exceptions.ProductoException;
import com.uniquindio.subastasUQ.exceptions.UsuarioException;
import com.uniquindio.subastasUQ.exceptions.pujaException;
import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.model.service.ISubastaUQService;
import com.uniquindio.subastasUQ.utils.ArchivoUtil;

import java.io.Serializable;
import java.util.ArrayList;

public class SubastaUq implements Serializable,ISubastaUQService {
    private static final long serialVersionUID = 1L;

    ArrayList<Usuario> listaUsuarios= new ArrayList<>();

    ArrayList<Producto> listaproductos= new ArrayList<>();

    ArrayList<Puja> listaProductosPuja= new ArrayList<>();
    ArrayList<Producto> listaProductosAdquiridos = new ArrayList<>();
    ArrayList<Anuncio> listaAnuncios = new ArrayList<>();

    public SubastaUq(){

    }

    public ArrayList<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    public ArrayList<Usuario> getListaUsuarios()
    {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Puja> getListaProductosPuja() {
        return listaProductosPuja;
    }

    public ArrayList<Producto> getListaProductosAdquiridos(String cedulaadquisicion) {
        ArrayList<Producto> pr= new ArrayList<>();
        for(Producto p:listaProductosAdquiridos){
            if(p.getCedulaAdquisicion().equals(cedulaadquisicion)){
                pr.add(p);
            }
        }
        return  pr;
    }

    public ArrayList<Producto> getListaProductosAdquiridos() {
        return listaProductosAdquiridos;
    }

    public void setListaProductosAdquiridos(ArrayList<Producto> listaProductosAdquiridos) {
        this.listaProductosAdquiridos = listaProductosAdquiridos;
    }

    public boolean verificarUsuarioExistente(String cedula) throws UsuarioException {
        if(usuarioExiste(cedula)){
            throw new UsuarioException("El empleado con cedula: "+cedula+" ya existe");
        }else{
            return false;
        }
    }

    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioException{
        getListaUsuarios().add(nuevoUsuario);
    }

    public boolean usuarioExiste(String cedula) {
        boolean UsuarioEncontrado = false;
        for (Usuario usuario : getListaUsuarios()) {
            if(usuario.getCedula().equalsIgnoreCase(cedula)){
                UsuarioEncontrado = true;
                break;
            }
        }
        return UsuarioEncontrado;
    }

    public String getNombre(String cedula) {
        String UsuarioEncontrado = "";
        for (Usuario usuario : getListaUsuarios()) {
            if(usuario.getCedula().equalsIgnoreCase(cedula)){
                UsuarioEncontrado = usuario.getNombre();
                break;
            }
        }
        return UsuarioEncontrado;
    }


    @Override
    public Usuario obtenerUsuario(String cedula) {
        Usuario usuarioEncontrado = null;
        for (Usuario empleado : getListaUsuarios()) {
            if(empleado.getCedula().equalsIgnoreCase(cedula)){
                usuarioEncontrado = empleado;
                break;
            }
        }
        return usuarioEncontrado;
    }

    @Override
    public Boolean eliminarUsuario(String cedula) throws UsuarioException {
        Usuario empleado = null;
        boolean flagExiste = false;
        empleado = obtenerUsuario(cedula);
        if(empleado == null)
            throw new UsuarioException("El usuario a eliminar no existe");
        else{
            getListaUsuarios().remove(empleado);
            flagExiste = true;
        }
        return flagExiste;
    }

    @Override
    public ArrayList<Usuario> obtenerEmpleados() {
        return listaUsuarios;
    }

    @Override
    public boolean actualizarUsuario(String cedulaActual, Usuario usurios) throws UsuarioException {
        Usuario usuarioActual = obtenerUsuario(cedulaActual);
        if(usuarioActual == null)
            throw new UsuarioException("El empleado a actualizar no existe");
        else{
            usuarioActual.setNombre(usurios.getNombre());
            usuarioActual.setCedula(usurios.getCedula());
            usuarioActual.setTelefono(usurios.getTelefono());
            usuarioActual.setEmail(usurios.getEmail());
            usuarioActual.setDireccion(usurios.getDireccion());
            usuarioActual.setContrasena(usurios.getContrasena());
            return true;
        }
    }

    public ArrayList<Producto> getListaproductos() {
        return listaproductos;
    }

    public ArrayList<Producto> getListaproductos(String cedulaAnunciante) {
        ArrayList<Producto> p= new ArrayList<>();

        for(Producto pr:listaproductos){
            if(pr.getCedulaAnunciante().equals(cedulaAnunciante)){
                p.add(pr);
            }
        }
    return p;}

    public void setListaproductos(ArrayList<Producto> listaproductos) {
        this.listaproductos = listaproductos;
    }

    public Producto obtenerProducto(String nombre){
        Producto pr= null;
        for(Producto p: getListaproductos()){
            if(p.getNombreProducto().equalsIgnoreCase(nombre)){
                pr=p;
                break;
            }
        }
        return pr;
    }

    public boolean eliminarProducto(String nombre) throws Exception {
        Producto pr = null;
        boolean flagExiste = false;
        pr = obtenerProducto(nombre);
        if(pr == null){
            throw new Exception("El Producto a eliminar no existe");
        }
        else{
            getListaproductos().remove(pr);
            flagExiste = true;
        }
        return flagExiste;
    }

    public boolean verificarProductoExiste (Producto producto)
    {
        boolean centinela=false;
        return centinela;
    }
    public void agregarProducto(Producto nuevoProducto) throws ProductoException{
        getListaproductos().add(nuevoProducto);
    }

    public ArrayList<Puja> getListaProductosPuja(String cedulaComprador) {
        ArrayList<Puja> p= new ArrayList<>();
        for(Puja pr: listaProductosPuja){
            if(pr.getCedulaComprador().equals(cedulaComprador)){
                p.add(pr);
            }
        }

        return p;
    }

    public ArrayList<Puja> getListaProductosPuja(String nombreProducto,String cedulaAnunciante) {
        ArrayList<Puja> p= new ArrayList<>();
        for(Puja pr: listaProductosPuja){
            if(pr.getNombreProducto().equals(nombreProducto)&&pr.getCedulaAnunciante().equals(cedulaAnunciante)){
                p.add(pr);
            }
        }

        return p;
    }

    public void setListaProductosPuja(ArrayList<Puja> listaProductosPuja) {
        this.listaProductosPuja = listaProductosPuja;
    }

    public boolean eliminarPuja(String nombre,String cedulaC,String cedulaA)throws pujaException {
        boolean f=false;
        Puja pr=obtenerPuja(nombre,cedulaC,cedulaA);
        if(pr==null){
            throw new pujaException("el producto no existe");
        }
        else{
            listaProductosPuja.remove(pr);
            f=true;
        }
    return f;}

    public Puja obtenerPuja(String nombre,String cedulaC, String cedulaA){
        Puja pr=null;
        for(Puja p:listaProductosPuja){
            if(p.getNombreProducto().equalsIgnoreCase(nombre)&&p.getCedulaComprador().equalsIgnoreCase(cedulaC)&&p.getCedulaAnunciante().equalsIgnoreCase(cedulaA)){
                pr=p;
                break;
            }
        }
    return pr;}

    public void agregarPuja(Puja puja){
        listaProductosPuja.add(puja);
    }

    public boolean verificarCantidadPujas(String n){
        boolean f=false;
        int c=0;
        for(Puja p:listaProductosPuja){
            if(p.getCedulaAnunciante().equalsIgnoreCase(n)){
                c++;
            }
        }
        if(c>=3){
            f=true;
        }

    return f;}
public void agregarAnuncio(Anuncio anuncio) {

    listaAnuncios.add(anuncio);
    System.out.println("Se agrego con exito");
}

    public void setValorinicial(String nombrep,String cedulaAnunciante,String valorpuja){

        for(Producto p:listaproductos){
            if(p.getCedulaAnunciante().equals(cedulaAnunciante)&&p.getNombreProducto().equals(nombrep)){
                p.setValorInicial(valorpuja);
            }
        }

    }

    public void agregarProductoAdquirido(String nombreP, String cedulaA,String cedulaC){

        for(int i=0;i<listaproductos.size();i++){
            if(listaproductos.get(i).getNombreProducto().equalsIgnoreCase(nombreP)&&listaproductos.get(i).getCedulaAnunciante().equalsIgnoreCase(cedulaA)){
                listaproductos.get(i).setCedulaAdquisicion(cedulaC);
                listaproductos.get(i).setFechaAdquirido(ArchivoUtil.cargarFechaSistema());
                listaProductosAdquiridos.add(listaproductos.get(i));
                listaproductos.remove(listaproductos.get(i));

            }
        }

        for(Anuncio a:listaAnuncios){
            if(a.getCedulaAnunciante().equalsIgnoreCase(cedulaA)){
                listaAnuncios.remove(a);
                break;
            }
        }
    }

}



