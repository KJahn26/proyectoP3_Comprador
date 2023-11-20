package com.uniquindio.subastasUQ.utils;

import com.uniquindio.subastasUQ.model.Producto;
import com.uniquindio.subastasUQ.model.SubastaUq;
import com.uniquindio.subastasUQ.model.Usuario;

public class subastaUqUtils {

    public static SubastaUq inicializarDatos(){


        SubastaUq subastaUq= new SubastaUq();

        inicializarusuarios(subastaUq);

        inicializarProductos(subastaUq);

        return subastaUq; }



    public static void inicializarusuarios(SubastaUq subastaUq) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Jean");

        usuario.setCedula("1120839058");
        usuario.setDireccion("peatonal UQ");
        usuario.setEmail("jeank.mendezc@uqvirtual.edu.co");
        usuario.setContrasena("megustas");
        usuario.setConfirmacioncontrasena("megustas");
        usuario.setTelefono("3223936041");
        subastaUq.getListaUsuarios().add(usuario);

        usuario = new Usuario();
        usuario.setNombre("Jahn");
        usuario.setCedula("1096431233");
        usuario.setDireccion("tebaida");
        usuario.setEmail("jahnc.martinezv@uqvirtual.edu.co");
        usuario.setContrasena("boluda");
        usuario.setConfirmacioncontrasena("boluda");
        usuario.setTelefono("3112349876");
        subastaUq.getListaUsuarios().add(usuario);

        usuario = new Usuario();
        usuario.setNombre("sota");
        usuario.setCedula("7237493274");
        usuario.setDireccion("noseasapo");
        usuario.setEmail("lalalala@uqvirtual.edu.co");
        usuario.setContrasena("contraseña");
        usuario.setConfirmacioncontrasena("contraseña");
        usuario.setTelefono("3675728747");
        subastaUq.getListaUsuarios().add(usuario);

    }

    public static void inicializarProductos(SubastaUq subastaUq){

        Producto producto= new Producto();
        producto.setNombreProducto("auto");
        producto.setTipoProducto("vehiculo");
        producto.setDescProducto("4x4 bien agresivo");
        producto.setAnunciante("carlos");
        subastaUq.getListaproductos().add(producto);

        producto = new Producto();
        producto.setNombreProducto("rayo mcqueen");
        producto.setTipoProducto("juguete");
        producto.setDescProducto("juguete del canal del raton");
        producto.setAnunciante("laura");
        producto.setFechaTerminarPublicacion("2023-11-04");
        producto.setValorInicial("10");
        subastaUq.getListaproductos().add(producto);

        producto = new Producto();
        producto.setNombreProducto("prueba");
        producto.setTipoProducto("algo");
        producto.setDescProducto("aja");
        producto.setAnunciante("sota");
        producto.setCedulaAnunciante("7237493274");
        producto.setFechaTerminarPublicacion("2023-11-05");
        producto.setValorInicial("10");
        subastaUq.getListaproductos().add(producto);
    }

}
