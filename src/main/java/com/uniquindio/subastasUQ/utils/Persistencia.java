package com.uniquindio.subastasUQ.utils;

import com.uniquindio.subastasUQ.model.Producto;
import com.uniquindio.subastasUQ.model.SubastaUq;
import com.uniquindio.subastasUQ.model.Usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia {

    public static final String RUTA_USUARIOSTxt = "src/main/resources/persistencia/archivos/Archivos/archivoUsuarios.txt";
    public static final String RUTA_USUARIOSXML="src/main/resources/persistencia/archivos/model.xml";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO ="2023Proyecto/src/main/resources/persistencia/archivos/dato.dat";

    public static final String RUTA_SUBASTA_LOG="src/main/resources/persistencia/archivos/log/SubastaLog.txt";

    public static final String RUTA_TRANSACCIONESTxt = "src/main/resources/persistencia/archivos/Archivos/objeto_Transaccion.txt";
    public static final String RUTA_PRODUCTOS ="src/main/resources/persistencia/archivos/Productos.xml";

    


    public static void cargarDatosArchisvos(SubastaUq subasta) throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuariosCargados = cargarUsuarios();
        if (usuariosCargados.size() > 0) {
            subasta.getListaUsuarios().addAll(usuariosCargados);
        }


    }

    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        String datos = "";
        for (Usuario s : usuarios) {
            datos = datos + (s.getNombre() + "@" + s.getCedula() + "@" + s.getTelefono() +
                    "@" + s.getDireccion() + "@" + s.getEmail() + "@" + s.getContrasena() + "\n");
        }
        ArchivoUtil.agregarArchivo(RUTA_USUARIOSTxt, datos, false);
    }

    public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_USUARIOSTxt);
        String centinela = "";
        for (int i = 0; i < contenido.size(); i += 1) {
            centinela = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setNombre(centinela.split("@")[0]);
            usuario.setCedula(centinela.split("@")[1]);
            usuario.setTelefono(centinela.split("@")[2]);
            usuario.setDireccion(centinela.split("@")[3]);
            usuario.setEmail(centinela.split("@")[4]+"@"+centinela.split("@")[5]);
            usuario.setContrasena(centinela.split("@")[6]);
            usuarios.add(usuario);
        }
        return usuarios;

    }

    public static void guardarTransacciones(String mensaje){
       // ArchivoUtil.salvaguardarTransaccion(mensaje,RUTA_TRANSACCIONESTxt);
        ArchivoUtil.agregarArchivo(RUTA_TRANSACCIONESTxt,mensaje,false);

    }

    public static void guardaRegistroLog(String tipoUsuario,int nivel,String accion,String interfaz){
        ArchivoUtil.guardarRegistroLog(tipoUsuario,nivel,accion,interfaz,RUTA_SUBASTA_LOG);
    }

    public static SubastaUq cargarRecursoBancoXML() {

        SubastaUq usuario = null;

        try {
            usuario = (SubastaUq) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_USUARIOSXML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return usuario;



    }


    public static void guardarRecursoBancoXML(SubastaUq subastaUq) {
        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_USUARIOSXML, subastaUq);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static SubastaUq cargarRecursoBancoBinario() {
        SubastaUq banco = null;

        try {
            banco = (SubastaUq) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return banco;
    }

    public static void guardarRecursoBancoBinario(SubastaUq subastaUq) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO, subastaUq);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
