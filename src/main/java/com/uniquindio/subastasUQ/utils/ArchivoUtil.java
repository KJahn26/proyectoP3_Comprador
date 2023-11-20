package com.uniquindio.subastasUQ.utils;

import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.model.Producto;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class ArchivoUtil
{
    static String fechaSistema="";
        public static void agregarArchivo (String ruta,String contenido, Boolean AnexarContenido)
        {
                try
                {
                    FileWriter fw = new FileWriter(ruta,AnexarContenido);
                    BufferedWriter fr = new BufferedWriter(fw);
                    fr.write(contenido);
                    fr.close();
                    fw.close();
                }catch (IOException e)
                {
                    System.out.println("EL error E/s"+e);
                }
        }

        public static ArrayList<String> leerArchivo (String ruta)
        {
            ArrayList<String> contenido = new ArrayList<>();

                try
                {
                    FileReader fr = new FileReader(ruta);
                    BufferedReader fw = new BufferedReader(fr);
                    String centinela="";
                    centinela=fw.readLine();
                    while (centinela!=null)
                    {
                        contenido.add(centinela);
                        centinela=fw.readLine();
                    }
                    fr.close();
                    fw.close();
                }catch ( IOException e)
                {
                    System.out.println("EL error E/s" +e);
                }
                return contenido;
        }

    /*    public static void salvaguardarTransaccion(String msj,String ruta){
        try{
            FileWriter fr= new FileWriter(ruta,false);
            BufferedWriter br= new BufferedWriter(fr);
            cargarFechaSistema();
            br.write(msj+". Fecha: "+fechaSistema);
            br.close();
            fr.close();

        }catch(Exception e){
            System.out.println("EL error E/s" +e);
            }

        }*/


    public static void guardarRegistroLog(String tipoUsuario, int nivel, String accion,String interfaz, String rutaArchivo)
    {
        String log = "";
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler =  null;
        cargarFechaSistema();
        try {
            fileHandler = new FileHandler(rutaArchivo,true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            switch (nivel) {
                case 1:
                    LOGGER.log(Level.INFO,tipoUsuario+" REALIZO: "+accion+" en "+interfaz+" , FECHA: "+fechaSistema) ;
                    break;

                case 2:
                    LOGGER.log(Level.WARNING,tipoUsuario+" REALIZO: "+accion+" en "+interfaz+" , FECHA: "+fechaSistema) ;
                    break;

                case 3:
                    LOGGER.log(Level.SEVERE,tipoUsuario+" REALIZO: "+accion+" en "+interfaz+" , FECHA: "+fechaSistema) ;
                    break;

                default:
                    break;
            }

        } catch (SecurityException e) {

            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        }
        finally {

            fileHandler.close();
        }
    }

    public static String cargarFechaSistema() {

        String diaN = "";
        String mesN = "";
        String añoN = "";

        Calendar cal1 = Calendar.getInstance();
        LocalDateTime locaDate = LocalDateTime.now();
        int hours  = locaDate.getHour();
        String amPm = (hours >= 12) ? "PM" : "AM";
        int minutes = locaDate.getMinute();
        int seconds = locaDate.getSecond();


        int  dia = cal1.get(Calendar.DATE);
        int mes = cal1.get(Calendar.MONTH)+1;
        int año = cal1.get(Calendar.YEAR);
        //int hora = cal1.get(Calendar.HOUR);
        //int minuto = cal1.get(Calendar.MINUTE);

        if(dia < 10){
            diaN+="0"+dia;
        }
        else{
            diaN+=""+dia;
        }
        if(mes < 10){
            mesN+="0"+mes;
        }
        else{
            mesN+=""+mes;
        }

        //		fecha_Actual+= año+"-"+mesN+"-"+ diaN;
        //		fechaSistema = año+"-"+mesN+"-"+diaN+"-"+hora+"-"+minuto;
        fechaSistema = año+" - "+mesN+" - "+diaN+ " hora  "+hours  + ":"+ minutes +":"+seconds +" "+ amPm;
        //		horaFechaSistema = hora+"-"+minuto;
        return fechaSistema;
    }







    public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

        XMLDecoder decodificadorXML;
        Object objetoXML;

        decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
        objetoXML = decodificadorXML.readObject();
        decodificadorXML.close();
        return objetoXML;

    }

    public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {

        XMLEncoder codificadorXML;

        codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
        codificadorXML.writeObject(objeto);
        codificadorXML.close();

    }

    public static Object cargarRecursoSerializado(String rutaArchivo) throws IOException, ClassNotFoundException {
        Object aux = null;
//		Empresa empresa = null;
        ObjectInputStream ois = null;
        try {
            // Se crea un ObjectInputStream
            ois = new ObjectInputStream(new FileInputStream(rutaArchivo));

            aux = ois.readObject();

        } catch (Exception e2) {
            throw e2;
        } finally {
            if (ois != null)
                ois.close();
        }
        return aux;
    }

    public static void salvarRecursoSerializado(String rutaArchivo, Object object) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            oos.writeObject(object);
        } catch (Exception e) {
            throw e;
        } finally {
            if (oos != null)
                oos.close();
        }
    }




}
